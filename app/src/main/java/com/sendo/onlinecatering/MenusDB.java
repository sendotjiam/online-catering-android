package com.sendo.onlinecatering;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MenusDB {
    private UserDBHelper dbHelper;

    public MenusDB(Context ctx){
        dbHelper = new UserDBHelper(ctx);
    }

    public void insertMenus(Menus menus){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UserDBHelper.FIELD_MENU_NAME, menus.getMenu_name());
        cv.put(UserDBHelper.FIELD_MENU_IMAGE_PATH, menus.getMenu_img_path());
        cv.put(UserDBHelper.FIELD_MENU_PRICE, menus.getMenu_price());
        cv.put(UserDBHelper.FIELD_MENU_DESCRIPTION, menus.getMenu_description());

        db.insert(UserDBHelper.TABLE_MENU, null, cv );

        db.close();
    }
}
