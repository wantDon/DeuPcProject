package com.example.demo.inventory.invenDTO;

public class InvenDTO {
    private int food_num;
    private String food_group;
    private String food_name;
    private int food_price;
    private int count;
    private int state;
    private String food_info;
    private String food_img;

    public String getFood_info() {
        return food_info;
    }

    public void setFood_info(String food_info) {
        this.food_info = food_info;
    }


    public int getFood_num() {
        return food_num;
    }

    public void setFood_num(int food_num) {
        this.food_num = food_num;
    }

    public String getFood_group() {
        return food_group;
    }

    public void setFood_group(String food_group) {
        this.food_group = food_group;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getFood_price() {
        return food_price;
    }

    public void setFood_price(int food_price) {
        this.food_price = food_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }



    public String getFood_img() {
        return food_img;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }
}
