package com.example.demo.notice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.notice.dto.NoticeDTO;
import com.example.demo.notice.mapper.NoticeMapper;

@Service
public class NoticeServiceImple implements NoticeService {

	private NoticeMapper noticeMapper;
	
	public NoticeServiceImple(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	public List getListNotice() {
		return noticeMapper.getAllNotices();
	}
	
	@Override
	public int getTotalNoticeCount() {
        return noticeMapper.getTotalNoticeCount();
    }

	@Override
    public List<NoticeDTO> getListNoticeByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return noticeMapper.getNoticesByPage(offset, pageSize);
    }
	
    @Override
    public NoticeDTO getNoticeDetail(int notice_num) {
        return noticeMapper.getNoticeDetail(notice_num); // NoticeMapper에 새로 추가한 메소드 호출
    }
    
    // 이전 공지사항의 번호를 가져오는 메서드
    public Integer getPreviousNoticeNum(int notice_num) {
        Integer previousNoticeNum = noticeMapper.getPreviousNoticeNum(notice_num);
        return previousNoticeNum;
    }
    
    // 다음 공지사항의 번호를 가져오는 메서드
    public Integer getNextNoticeNum(int notice_num) {
        Integer nextNoticeNum = noticeMapper.getNextNoticeNum(notice_num);
        return nextNoticeNum;
    }
    
    // 공지사항 추가 메서드
    @Override
    public void insertNotice(NoticeDTO notice) {
        if (notice.getNotice_date() == null) {
            notice.setNotice_date(LocalDateTime.now());
        }
        noticeMapper.insertNotice(notice);
    }

    //공지사항 삭제 메서드
    @Override
    public void deleteNotice(int notice_num) {
        noticeMapper.deleteNotice(notice_num);
    }
    
}
