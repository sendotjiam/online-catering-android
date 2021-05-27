package Admin;

import java.io.Serializable;

public class OrderList implements Serializable {
    private String order_id;
    private String order_user_id;
    private String order_code;
    private String order_menu_name;
    private String order_menu_price;
    private String order_transaction_date;
    private String order_status;

    public OrderList(String order_id, String order_user_id, String order_code, String order_menu_name, String order_menu_price, String order_transaction_date, String order_status) {
        this.order_id = order_id;
        this.order_user_id = order_user_id;
        this.order_code = order_code;
        this.order_menu_name = order_menu_name;
        this.order_menu_price = order_menu_price;
        this.order_transaction_date = order_transaction_date;
        this.order_status = order_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_user_id() {
        return order_user_id;
    }

    public void setOrder_user_id(String order_user_id) {
        this.order_user_id = order_user_id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getOrder_menu_name() {
        return order_menu_name;
    }

    public void setOrder_menu_name(String order_menu_name) {
        this.order_menu_name = order_menu_name;
    }

    public String getOrder_menu_price() {
        return order_menu_price;
    }

    public void setOrder_menu_price(String order_menu_price) {
        this.order_menu_price = order_menu_price;
    }

    public String getOrder_transaction_date() {
        return order_transaction_date;
    }

    public void setOrder_transaction_date(String order_transaction_date) {
        this.order_transaction_date = order_transaction_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
