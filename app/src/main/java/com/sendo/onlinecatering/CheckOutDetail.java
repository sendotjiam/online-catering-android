package com.sendo.onlinecatering;

public class CheckOutDetail {
    private Integer image_src;
    private String fnbname;
    private String fnbdetail;
    private String notes;
    private String date;
    private String fnbprice;

    public CheckOutDetail(Integer image_src, String fnbname, String fnbdetail, String notes, String date, String fnbprice) {
        this.image_src = image_src;
        this.fnbname = fnbname;
        this.fnbdetail = fnbdetail;
        this.notes = notes;
        this.date = date;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFnbprice() {
        return fnbprice;
    }

    public void setFnbprice(String fnbprice) {
        this.fnbprice = fnbprice;
    }
}
