package com.sendo.onlinecatering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CartDB {
    private DBHelper dbHelper;
    Context context;

    public CartDB(Context ctx){
        dbHelper = new DBHelper(ctx);
        this.context = ctx;
    }

    public void insertCart(Cart cart){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.FIELD_CART_USER_ID, cart.getUser_id());
        cv.put(DBHelper.FIELD_CART_MENU_ID, cart.getMenu_id());

        db.insert(DBHelper.TABLE_CART, null, cv );

        db.close();
    }

    public void addToCart(int userId, int menuId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_CART_USER_ID, userId);
        cv.put(DBHelper.FIELD_CART_MENU_ID, menuId);
        db.insert(DBHelper.TABLE_CART, null, cv);
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

        if (cursor.moveToFirst()) {
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


    public void deleteCartItem(String menuName) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        MenusDB menusDB = new MenusDB(context);
        int menuId = menusDB.getMenuIdByName(menuName);
        sqLiteDatabase.delete(DBHelper.TABLE_CART, DBHelper.FIELD_CART_MENU_ID, new String[]{menuId + ""});
        sqLiteDatabase.close();
    }

}
