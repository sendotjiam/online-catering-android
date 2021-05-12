package com.sendo.onlinecatering;

public class FnBDetail {
    private String fnbname;
    private String fnbprice;

    public FnBDetail(String fnbname, String fnbprice) {
        this.fnbname = fnbname;
        this.fnbprice = fnbprice;
    }

    public String getFnbname() {
        return fnbname;
    }

    public void setFnbname(String fnbname) {
        this.fnbname = fnbname;
    }

    public String getFnbprice() {
        return fnbprice;
    }

    public void setFnbprice(String fnbprice) {
        this.fnbprice = fnbprice;
    }
}
