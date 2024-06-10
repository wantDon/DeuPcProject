package com.example.demo.myprofile.myprofileDTO;

import com.example.demo.myprofile.myprofileDTO.orderDTO;

import java.util.ArrayList;
import java.util.List;

public class paymentDTO {
    private List<orderDTO> orders = new ArrayList<>();
    private List<foodDTO> foods = new ArrayList<>();
    private int pay_num;
    private int total_price;
    private String method;
    private String pay_date;
    private String pay_req;
    private int pay_div;
    private int pay_state;
    private String id;
    private String food_name;

    public List<foodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<foodDTO> foods) {
        this.foods = foods;
    }

    public List<orderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<orderDTO> orders) {
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
    public String getFood_name() {
        return food_name;
    }
    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }
}
