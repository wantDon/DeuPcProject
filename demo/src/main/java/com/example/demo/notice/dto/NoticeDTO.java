package com.example.demo.notice.dto;

import java.time.LocalDateTime;

public class NoticeDTO {
    private int notice_num;
    private String notice_title;
    private String notice_content;
    private LocalDateTime notice_date;

    // Getter와 Setter 메서드
    public int getNotice_num() {
        return notice_num;
    }

    public void setNotice_num(int notice_num) {
        this.notice_num = notice_num;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public LocalDateTime getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(LocalDateTime notice_date) {
        this.notice_date = notice_date;
    }
}
