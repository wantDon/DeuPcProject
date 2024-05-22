package com.example.demo.notice.service;

import com.example.demo.notice.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {

    List<NoticeDTO> getListNotice();
    
    int getTotalNoticeCount();
    
    List<NoticeDTO> getListNoticeByPage(int page, int pageSize);
    
    NoticeDTO getNoticeDetail(int notice_num); // 공지사항 번호로 상세 정보 가져오기
    
    Integer getPreviousNoticeNum(int notice_num); // 이전 공지사항 번호 가져오기
    
    Integer getNextNoticeNum(int notice_num); // 다음 공지사항 번호 가져오기
    
    void insertNotice(NoticeDTO notice); // 공지사항 추가
    
    void deleteNotice(int notice_num); // 공지사항 삭제
}
