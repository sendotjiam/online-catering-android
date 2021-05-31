package com.sendo.onlinecatering;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class MenusDB {
    private UserDBHelper dbHelper;

    public MenusDB(Context ctx){
        dbHelper = new UserDBHelper(ctx);
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
    }
}
