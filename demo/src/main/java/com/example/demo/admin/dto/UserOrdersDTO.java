package com.example.demo.admin.dto;

public class UserOrdersDTO {

	private int pay_num;
	private int total_price;
	private String pay_date;
	private String pay_req;
	private int pay_state;
	private String id;
	private int food_num;
	private int order_count;
	private String food_name;
	private String pcnum;
	
	public int getPay_num() {
		return pay_num;
	}
	public void setPay_num(int pay_num) {
		this.pay_num = pay_num;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getPay_req() {
		return pay_req;
	}
	public void setPay_req(String pay_req) {
		this.pay_req = pay_req;
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
	public int getFood_num() {
		return food_num;
	}
	public void setFood_num(int food_num) {
		this.food_num = food_num;
	}
	public int getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getPcnum() {
		return pcnum;
	}
	public void setPcnum(String pcnum) {
		this.pcnum = pcnum;
	}
	
}