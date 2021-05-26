package com.sendo.onlinecatering;

public class CheckOutDetail {
    private Integer image_src;
    private String fnbname;
    private String fnbdetail;
    private String fnbname2;
    private String fnbprice;

    public CheckOutDetail(Integer image_src, String fnbname, String fnbdetail, String fnbname2, String fnbprice) {
        this.image_src = image_src;
        this.fnbname = fnbname;
        this.fnbdetail = fnbdetail;
        this.fnbname2 = fnbname2;
        this.fnbprice = fnbprice;
    }

    public Integer getImage_src() {
        return image_src;
    }

    public void setImage_src(Integer image_src) {
        this.image_src = image_src;
    }

    public String getFnbname() {
        return fnbname;
    }

    public void setFnbname(String fnbname) {
        this.fnbname = fnbname;
    }

    public String getFnbdetail() {
        return fnbdetail;
    }

    public void setFnbdetail(String fnbdetail) {
        this.fnbdetail = fnbdetail;
    }

    public String getFnbname2() {
        return fnbname2;
    }

    public void setFnbname2(String fnbname2) {
        this.fnbname2 = fnbname2;
    }

    public String getFnbprice() {
        return fnbprice;
    }

    public void setFnbprice(String fnbprice) {
        this.fnbprice = fnbprice;
    }
}
