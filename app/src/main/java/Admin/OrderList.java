package Admin;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderList implements Serializable {
    private String order_code;
    private String date;
    private String status;

    public OrderList(String order_code, String date, String status) {
        this.order_code = order_code;
        this.date = date;
        this.status = status;
    }

    public String getOrder_Code() {
        return order_code;
    }

    public void setOrder_Code(String order_code) {
        this.order_code = order_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
