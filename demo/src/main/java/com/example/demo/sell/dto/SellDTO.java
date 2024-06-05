package com.example.demo.sell.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SellDTO {

	private int pay_num;
	private LocalDateTime pay_date;
	private String cate_name;
	private String food_name;
	private int order_count;
	private int food_price;
	private int total_price;
	private String id;
	private String method;
	private int pay_div;
	
	public int getPay_num() {
		return pay_num;
	}
	public void setPay_num(int pay_num) {
		this.pay_num = pay_num;
	}
	// 추가: pay_date를 String으로 변환하는 메서드
    public String getFormattedPayDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return pay_date.format(formatter);
    }
	public void setPay_date(LocalDateTime pay_date) {
		this.pay_date = pay_date;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public int getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}
	public int getFood_price() {
		return food_price;
	}
	public void setFood_price(int food_price) {
		this.food_price = food_price;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getPay_div() {
		return pay_div;
	}
	public void setPay_div(int pay_div) {
		this.pay_div = pay_div;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
