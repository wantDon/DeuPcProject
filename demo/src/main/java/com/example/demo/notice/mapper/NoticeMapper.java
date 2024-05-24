package com.example.demo.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.notice.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {

    List<NoticeDTO> getAllNotices();

    @Select("SELECT COUNT(*) FROM notice")
    int getTotalNoticeCount();

    @Select("SELECT * FROM notice LIMIT #{offset}, #{pageSize}")
    List<NoticeDTO> getNoticesByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM notice WHERE notice_num = #{notice_num}")
    NoticeDTO getNoticeDetail(int notice_num);

    // 이전 공지사항의 번호를 가져오는 메서드
    Integer getPreviousNoticeNum(@Param("notice_num") int notice_num);
    
    // 다음 공지사항의 번호를 가져오는 메서드
    Integer getNextNoticeNum(@Param("notice_num") int notice_num);
    
    // 공지사항 추가 메서드
    void insertNotice(NoticeDTO notice);
    
    // 공지사항 삭제 메서드
    void deleteNotice(@Param("notice_num") int notice_num);
}
	