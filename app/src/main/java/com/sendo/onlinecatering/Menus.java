package com.sendo.onlinecatering;

import android.os.Parcel;
import android.os.Parcelable;

public class Menus implements Parcelable {
    int menu_id;
    String menu_name;
    String menu_img_path;
    long menu_price;
    String menu_description;

    public Menus(int menu_id, String menu_name, String menu_img_path, long menu_price, String menu_description) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_img_path = menu_img_path;
        this.menu_price = menu_price;
        this.menu_description = menu_description;
    }

    public Menus(){

    }

    protected Menus(Parcel in) {
        menu_id = in.readInt();
        menu_name = in.readString();
        menu_img_path = in.readString();
        menu_price = in.readLong();
        menu_description = in.readString();
    }

    public static final Creator<Menus> CREATOR = new Creator<Menus>() {
        @Override
        public Menus createFromParcel(Parcel in) {
            return new Menus(in);
        }

        @Override
        public Menus[] newArray(int size) {
            return new Menus[size];
        }
    };

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_img_path() {
        return menu_img_path;
    }

    public void setMenu_img_path(String menu_img_path) {
        this.menu_img_path = menu_img_path;
    }

    public long getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(long menu_price) {
        this.menu_price = menu_price;
    }

    public String getMenu_description() {
        return menu_description;
    }

    public void setMenu_description(String menu_description) {
        this.menu_description = menu_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(menu_id);
        parcel.writeString(menu_name);
        parcel.writeString(menu_img_path);
        parcel.writeLong(menu_price);
        parcel.writeString(menu_description);
    }
}
