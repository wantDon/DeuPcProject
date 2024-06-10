package com.example.demo.myprofile.myprofileDTO;

import java.util.List;

public class orderDTO {
    private int orderNum;
    private int payNum;
    private int foodNum;
    private int orderCount;
    private String foodName;
    private int foodPrice;
    private String payDate;
    private int totalPrice;
    private List<foodDTO> foods;

    public List<foodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<foodDTO> foods) {
        this.foods = foods;
    }
// 생성자, getter 및 setter 생략

    // 추가된 생성자, getter 및 setter
    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getPayNum() {
        return payNum;
    }

    public void setPayNum(int payNum) {
        this.payNum = payNum;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
