package com.example.demo.counter.DTO;

import java.time.LocalDateTime;

public class PaymentDTO {
    private int pay_num;
    private int pay_price;
    private String method;
    private LocalDateTime pay_date;
    private int pay_div;
    private int pay_state;
    private String id;

    public int getPay_num() {
        return pay_num;
    }

    public void setPay_num(int pay_num) {
        this.pay_num = pay_num;
    }

    public int getPay_price() {
        return pay_price;
    }

    public void setPay_price(int pay_price) {
        this.pay_price = pay_price;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getPay_date() {
        return pay_date;
    }

    public void setPay_date(LocalDateTime pay_date) {
        this.pay_date = pay_date;
    }

    public int getPay_div() {
        return pay_div;
    }

    public void setPay_div(int pay_div) {
        this.pay_div = pay_div;
    }

    public int getPay_state() {
        return pay_state;
    }

    public void setPay_state(int pay_state) {
        this.pay_state = pay_state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
