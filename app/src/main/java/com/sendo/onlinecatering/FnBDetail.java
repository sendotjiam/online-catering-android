package com.sendo.onlinecatering;

import android.os.Parcel;
import android.os.Parcelable;

public class FnBDetail implements Parcelable {
    private String fnbname;
    private long fnbprice;

    public FnBDetail(String fnbname, long fnbprice) {
        this.fnbname = fnbname;
        this.fnbprice = fnbprice;
    }

    public FnBDetail(){

    }

    protected FnBDetail(Parcel in) {
        fnbname = in.readString();
        fnbprice = in.readLong();
    }

    public static final Creator<FnBDetail> CREATOR = new Creator<FnBDetail>() {
        @Override
        public FnBDetail createFromParcel(Parcel in) {
            return new FnBDetail(in);
        }

        @Override
        public FnBDetail[] newArray(int size) {
            return new FnBDetail[size];
        }
    };

    public String getFnbname() {
        return fnbname;
    }

    public void setFnbname(String fnbname) {
        this.fnbname = fnbname;
    }

    public long getFnbprice() {
        return fnbprice;
    }

    public void setFnbprice(long fnbprice) {
        this.fnbprice = fnbprice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fnbname);
        parcel.writeLong(fnbprice);
    }
}
