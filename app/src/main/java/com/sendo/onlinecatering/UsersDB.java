package com.sendo.onlinecatering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersDB {
    private UserDBHelper dbHelper;

    public UsersDB(Context ctx){
        dbHelper = new UserDBHelper(ctx);
    }

    public void insertUsers(Users users){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UserDBHelper.FIELD_USER_USERNAME, users.username);
        cv.put(UserDBHelper.FIELD_USER_PASSWORD, users.password);
        cv.put(UserDBHelper.FIELD_USER_PHONE, users.phone);
        cv.put(UserDBHelper.FIELD_USER_WALLET, users.wallet);
        cv.put(UserDBHelper.FIELD_USER_GENDER, users.gender);
        cv.put(UserDBHelper.FIELD_USER_DATEBIRTH, users.dateOfBirth);

        db.insert(UserDBHelper.TABLE_USERS, null, cv );

        db.close();
    }

    public boolean checkUsers(String username, String password){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "username=? AND password=?";
        String[] selectionargs = {"" + username, "" + password};
        int check = 0;
        Cursor cursor = db.query(UserDBHelper.TABLE_USERS, null, selection, selectionargs,null, null ,null);

        if (cursor.moveToFirst()){
            return true;
        }
        else return false;
    }

    public Users getUser(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "id=?";
        String[] selectionArgs = {"" + id};

        Cursor cursor = db.query(dbHelper.TABLE_USERS, null, selection, selectionArgs, null, null, null);

        Users users = null;

        if (cursor.moveToFirst()) {
            users = new Users();
            users.setId(cursor.getInt(cursor.getColumnIndex(dbHelper.FIELD_USER_ID)));
            users.setUsername(cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_USER_USERNAME)));
            users.setPassword(cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_USER_PASSWORD)));
            users.setPhone(cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_USER_PHONE)));
            users.setGender(cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_USER_GENDER)));
            users.setDateOfBirth(cursor.getString(cursor.getColumnIndex(dbHelper.FIELD_USER_DATEBIRTH)));
            users.setWallet(cursor.getLong(cursor.getColumnIndex(dbHelper.FIELD_USER_WALLET)));
        }

        cursor.close();
        db.close();
        return users;
    }

    public void updateNominal(Users users, int id, int nominal) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereClauseArgs = {"" + id};

        ContentValues cv = new ContentValues();
        int temp = (int) (users.getWallet() + nominal);
        cv.put(dbHelper.FIELD_USER_WALLET, temp);

        db.update(dbHelper.TABLE_USERS, cv, whereClause, whereClauseArgs);

        db.close();
    }

    public void minusNominal(Users users, int id, int nominal) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereClauseArgs = {"" + id};

        ContentValues cv = new ContentValues();
        int temp = (int) (users.getWallet() - nominal);
        cv.put(dbHelper.FIELD_USER_WALLET, temp);

        db.update(dbHelper.TABLE_USERS, cv, whereClause, whereClauseArgs);

        db.close();
    }

}
