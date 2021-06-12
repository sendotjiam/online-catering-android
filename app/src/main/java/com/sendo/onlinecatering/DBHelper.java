package com.sendo.onlinecatering;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Online_Catering.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_USERS = "Users";
    public static final String FIELD_USER_ID = "id";
    public static final String FIELD_USER_USERNAME = "username";
    public static final String FIELD_USER_PASSWORD = "password";
    public static final String FIELD_USER_PHONE = "phone";
    public static final String FIELD_USER_GENDER = "gender";
    public static final String FIELD_USER_WALLET = "wallet";
    public static final String FIELD_USER_DATEBIRTH = "DOB";

    private static final String CREATE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS +" (" +
            FIELD_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_USER_USERNAME + " TEXT," +
            FIELD_USER_PASSWORD + " TEXT," +
            FIELD_USER_PHONE + " INTEGER," +
            FIELD_USER_GENDER + " TEXT," +
            FIELD_USER_WALLET + " FLOAT,"+
            FIELD_USER_DATEBIRTH + " DATE)";

    private static final String DROP_USERS = "DROP TABLE IF EXISTS " + TABLE_USERS;

    public static final String TABLE_MENU = "Menu";
    public static final String FIELD_MENU_ID = "menu_id";
    public static final String FIELD_MENU_NAME = "menu_name";
    public static final String FIELD_MENU_IMAGE_PATH = "menu_image_path";
    public static final String FIELD_MENU_PRICE = "menu_price";
    public static final String FIELD_MENU_DESCRIPTION = "menu_description";

    private static final String CREATE_MENU = "CREATE TABLE IF NOT EXISTS " + TABLE_MENU +" (" +
            FIELD_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_MENU_NAME + " TEXT," +
            FIELD_MENU_IMAGE_PATH + " BLOB," +
            FIELD_MENU_PRICE + " FLOAT," +
            FIELD_MENU_DESCRIPTION + " TEXT)";

    private static final String DROP_MENU = "DROP TABLE IF EXISTS " + TABLE_MENU;

    public static final String TABLE_CART = "Cart";
    public static final String FIELD_CART_ID = "cart_id";
    public static final String FIELD_CART_USER_ID = "cart_user_id";
    public static final String FIELD_CART_MENU_ID = "cart_menu_id";

    private static final String CREATE_CART = "CREATE TABLE IF NOT EXISTS " + TABLE_CART +" (" +
            FIELD_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_CART_USER_ID + " INTEGER NOT NULL REFERENCES " + TABLE_USERS + "(" + FIELD_USER_ID + ") ON UPDATE CASCADE, " +
            FIELD_CART_MENU_ID + " INTEGER NOT NULL REFERENCES " + TABLE_MENU + "(" + FIELD_MENU_ID + ") ON UPDATE CASCADE)";

    private static final String DROP_CART = "DROP TABLE IF EXISTS " + TABLE_CART;

    public static final String TABLE_ORDER = "Order_table";
    public static final String FIELD_ORDER_ID = "order_id";
    public static final String FIELD_ORDER_USER_ID = "order_user_id";
    public static final String FIELD_ORDER_CODE = "order_code";
    public static final String FIELD_ORDER_MENU_NAME = "order_menu_name";
    public static final String FIELD_ORDER_MENU_PRICE = "order_menu_price";
    public static final String FIELD_ORDER_TRANSACTION_DATE = "order_transaction_date";
    public static final String FIELD_ORDER_STATUS = "order_status";

    private static final String CREATE_ORDER = "CREATE TABLE IF NOT EXISTS " + TABLE_ORDER +" (" +
            FIELD_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_ORDER_USER_ID + " INTEGER NOT NULL REFERENCES " + TABLE_USERS + "(" + FIELD_USER_ID + ") ON UPDATE CASCADE, " +
            FIELD_ORDER_CODE + " TEXT," +
            FIELD_ORDER_MENU_NAME + " TEXT," +
            FIELD_ORDER_MENU_PRICE + " TEXT," +
            FIELD_ORDER_TRANSACTION_DATE + " TEXT," +
            FIELD_ORDER_STATUS + " TEXT)";

    private static final String DROP_ORDER = "DROP TABLE IF EXISTS " + TABLE_ORDER;



    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_MENU);
        db.execSQL(CREATE_CART);
        db.execSQL(CREATE_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL(DROP_USERS);
        db.execSQL(DROP_MENU);
        db.execSQL(DROP_CART);
        db.execSQL(DROP_ORDER);
        onCreate(db);
    }
}
