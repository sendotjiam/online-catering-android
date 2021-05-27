package com.sendo.onlinecatering;

public class Cart {
    int cart_id;
    int user_id;
    int menu_id;

    public Cart(int cart_id, int user_id, int menu_id) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.menu_id = menu_id;
    }

    public Cart(){

    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
}
