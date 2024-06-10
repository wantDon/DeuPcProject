package com.example.demo.sell.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SellDTO {

	private int pay_num;
	private int food_num;
	private String pay_date;
	private String cate_name;
	private String food_name;
	private int order_count;
	private int total_sales;
	private int food_price;
	private int total_price;
	private int total_order_count;
	private String id;
	private String method;
	private int pay_div;
	private String pcnum;
	
	public int getPay_num() {
		return pay_num;
	}
	public void setPay_num(int pay_num) {
		this.pay_num = pay_num;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
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
	public int getFood_num() {
		return food_num;
	}
	public void setFood_num(int food_num) {
		this.food_num = food_num;
	}
	public int getTotal_sales() {
		return total_sales;
	}
	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}
	public int getTotal_order_count() {
		return total_order_count;
	}
	public void setTotal_order_count(int total_order_count) {
		this.total_order_count = total_order_count;
	}
	public String getPcnum() {
		return pcnum;
	}
	public void setPcnum(String pcnum) {
		this.pcnum = pcnum;
	}
}
