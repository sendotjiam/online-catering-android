package com.sendo.onlinecatering;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {

    private String name;
    private long price;

    public Menu() {

    }

    public Menu(String name, long price) {
        this.name = name;
        this.price = price;
    }

    protected Menu(Parcel in) {
        name = in.readString();
        price = in.readLong();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeLong(price);
    }
}
