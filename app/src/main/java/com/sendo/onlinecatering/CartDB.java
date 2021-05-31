package com.sendo.onlinecatering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Blob;
import java.util.ArrayList;

public class CartDB {
    private UserDBHelper dbHelper;

    public CartDB(Context ctx){
        dbHelper = new UserDBHelper(ctx);
    }

    public void insertCart(Cart cart){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UserDBHelper.FIELD_CART_USER_ID, cart.getUser_id());
        cv.put(UserDBHelper.FIELD_CART_MENU_ID, cart.getMenu_id());

        db.insert(UserDBHelper.TABLE_CART, null, cv );

        db.close();
    }

    public ArrayList<Menus> getMenu(int user_id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_menu = "SELECT * " +
                " FROM " + dbHelper.TABLE_MENU + " m " +
                " JOIN " + dbHelper.TABLE_CART + " c " +
                " ON m." + dbHelper.FIELD_MENU_ID + " = c." + dbHelper.FIELD_CART_MENU_ID +
                " WHERE c. " + dbHelper.FIELD_CART_USER_ID + " = " + user_id;

        Cursor cursor = db.rawQuery(get_menu, null);

        ArrayList<Menus> menus = new ArrayList<>();

        if (cursor.moveToFirst() == true) {
            do {
                int menuid = cursor.getInt(0);
                String menu_name = cursor.getString(1);
                byte[] menu_image_path = cursor.getBlob(2);
                long menu_price = cursor.getLong(3);
                String menu_description = cursor.getString(4);

                Menus menus1 = new Menus();
                menus1.setMenu_id(menuid);
                menus1.setMenu_name(menu_name);
                menus1.setMenu_img_path(menu_image_path);
                menus1.setMenu_price(menu_price);
                menus1.setMenu_description(menu_description);

                menus.add(menus1);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return menus;
    }

    public void deleteCart(int user_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String delete_cart = " DELETE FROM " + dbHelper.TABLE_CART +
                " WHERE " + dbHelper.FIELD_CART_USER_ID + " = " + user_id + ";";

        db.execSQL(delete_cart);
    }

}
