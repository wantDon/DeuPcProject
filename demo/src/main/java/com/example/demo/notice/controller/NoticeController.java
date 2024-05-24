package com.example.demo.notice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.demo.notice.dto.NoticeDTO;
import com.example.demo.notice.service.NoticeServiceImple;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeServiceImple noticeService;
	
	@Autowired
	public NoticeController(NoticeServiceImple noticeService) {
		this.noticeService = noticeService;
	}
	
	// 공지사항 목록 페이지 요청 처리
	@RequestMapping(value={"*", ""})
    public String notice(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String sword) {
        int pageSize = 10;
        int totalNotices = noticeService.getTotalNoticeCount();
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize);

        List<NoticeDTO> noticeList = noticeService.getListNoticeByPage(page, pageSize);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sword", sword);
        model.addAttribute("pageName", "공지사항");

        return "notice/notice";
    }
	
	// 공지사항 상세 페이지 요청 처리
    @RequestMapping("/detail")
    public String noticeDetail(Model model, @RequestParam int notice_num) {
        // 현재 공지사항 번호를 통해 상세 정보를 가져옵니다.
        NoticeDTO notice = noticeService.getNoticeDetail(notice_num);
        
        // 이전 공지사항의 번호를 가져옵니다.
        Integer previousNoticeNum = noticeService.getPreviousNoticeNum(notice_num);
        
        // 다음 공지사항의 번호를 가져옵니다.
        Integer nextNoticeNum = noticeService.getNextNoticeNum(notice_num);
        
        model.addAttribute("notice", notice);
        model.addAttribute("previousNoticeNum", previousNoticeNum);
        model.addAttribute("nextNoticeNum", nextNoticeNum);
        return "notice/notice_detail"; // 공지사항 상세 페이지로 이동합니다.
    }
    
    // 공지 작성 페이지로 이동
    @GetMapping("/notice_write")
    public String showWriteForm() {
        return "notice/notice_write";
    }

    // 공지 등록 요청 처리
    @PostMapping("/insert")
    public String insert(NoticeDTO data) {
        noticeService.insertNotice(data);
        return "redirect:/notice/";
    }
    
    // 공지 삭제 요청 처리
    @RequestMapping("/delete/{notice_num}")
    public String delete(@PathVariable int notice_num) {
        noticeService.deleteNotice(notice_num);
        return "redirect:/notice/";
    }
    
}
