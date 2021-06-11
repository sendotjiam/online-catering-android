package com.sendo.onlinecatering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class MenusDB {
    private DBHelper dbHelper;

    public MenusDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertMenus(String menu, byte[] image, long price, String menu_description){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String insert = "INSERT INTO " + dbHelper.TABLE_MENU + " VALUES (NULL, ?, ?, ?, ?)";

        SQLiteStatement sqLiteStatement = db.compileStatement(insert);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, menu);
        sqLiteStatement.bindBlob(2, image);
        sqLiteStatement.bindLong(3, price);
        sqLiteStatement.bindString(4, menu_description);

        sqLiteStatement.executeInsert();
        db.close();
    }

    public void updateMenus(int updateIndex, byte[] image, String name, long price, String desc){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        //ID sudah AutoIncrement jadi gaperlu diset
        cv.put(dbHelper.FIELD_MENU_NAME, name);
        cv.put(dbHelper.FIELD_MENU_IMAGE_PATH, image);
        cv.put(dbHelper.FIELD_MENU_PRICE, price);
        cv.put(dbHelper.FIELD_MENU_DESCRIPTION, desc);

        db.update(dbHelper.TABLE_MENU, cv, dbHelper.FIELD_MENU_ID+ " = ?", new String[]{updateIndex + ""});
    }

    public void deleteMenu(int deleteId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(dbHelper.TABLE_MENU, dbHelper.FIELD_MENU_ID+ " = ?", new String[]{deleteId + ""});
    }

    public ArrayList<Menus> getMenus() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Menus> menus = new ArrayList<>();

        String query = "SELECT * FROM " + dbHelper.TABLE_MENU;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Menus menu = new Menus();
                menu.setMenu_id(cursor.getInt(0));
                menu.setMenu_name(cursor.getString(1));
                menu.setMenu_img_path(cursor.getBlob(2));
                menu.setMenu_price(cursor.getInt(3));
                menu.setMenu_description(cursor.getString(4));

                menus.add(menu);

            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return menus;
    }

    public Menus getMenu(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Menus menu = new Menus();

        String query = "SELECT * FROM Menu WHERE menu_id = " + id;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                menu.setMenu_id(cursor.getInt(0));
                menu.setMenu_name(cursor.getString(1));
                menu.setMenu_img_path(cursor.getBlob(2));
                menu.setMenu_price(cursor.getInt(3));
                menu.setMenu_description(cursor.getString(4));

            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return menu;
    }

    public int getMenuIdByName(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int menuId = 0;

        String selection = "menu_name=?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query(DBHelper.TABLE_MENU, null, selection, selectionArgs, null, null, null, 1 + "");

        if (cursor.moveToFirst()) {
            menuId = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_MENU_ID));
        }
        cursor.close();
        db.close();
        return menuId;
    }
}
