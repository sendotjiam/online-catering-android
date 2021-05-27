package com.sendo.onlinecatering;

public class Order {
    int order_id;
    int user_id;
    String order_code;
    String menu_name;
    String menu_price;
    String transaction_date;
    String status;

    public Order(int order_id, int user_id, String order_code, String menu_name, String menu_price, String transaction_date, String status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_code = order_code;
        this.menu_name = menu_name;
        this.menu_price = menu_price;
        this.transaction_date = transaction_date;
        this.status = status;
    }

    public Order(){

    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
