package com.example.demo.user_order.userorderDTO;

import java.util.List;

public class PaymentDTO {
    private int pay_num;
    private int total_price;
    private String method;
    private String pay_date;
    private String pay_req;
    private int pay_div;
    private int pay_state;
    private String id;
    private List<OrderDTO> orders;
    private String pcnum;

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getPay_num() {
        return pay_num;
    }

    public void setPay_num(int pay_num) {
        this.pay_num = pay_num;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
	public String getPcnum() {
		return pcnum;
	}
	public void setPcnum(String pcnum) {
		this.pcnum = pcnum;
	}
}
