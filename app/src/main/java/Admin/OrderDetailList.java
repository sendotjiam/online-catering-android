package Admin;

public class OrderDetailList {
    private String foodname;
    private int foodprice;

    public OrderDetailList(String foodname, int foodprice) {
        this.foodname = foodname;
        this.foodprice = foodprice;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(int foodprice) {
        this.foodprice = foodprice;
    }
}
