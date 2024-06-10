package com.example.demo.myprofile.myprofileDTO;

public class myorderDTO {
    private String foodName;
    private int count;
    private String paymentDate;
    private int totalPrice;

    public myorderDTO() {
    }

    public myorderDTO(String foodName, int count, String paymentDate, int totalPrice) {
        this.foodName = foodName;
        this.count = count;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
