package Admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sendo.onlinecatering.DBHelper;

import java.util.ArrayList;

public class OrderDB {

    private DBHelper dbHelper;

    public OrderDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertUsers(OrderList order){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_ORDER_ID, order.getOrder_id());
        cv.put(DBHelper.FIELD_ORDER_USER_ID, order.getOrder_user_id());
        cv.put(DBHelper.FIELD_ORDER_CODE, order.getOrder_code());
        cv.put(DBHelper.FIELD_ORDER_MENU_NAME, order.getOrder_menu_name());
        cv.put(DBHelper.FIELD_ORDER_MENU_PRICE, order.getOrder_menu_price());
        cv.put(DBHelper.FIELD_ORDER_TRANSACTION_DATE, order.getOrder_transaction_date());
        cv.put(DBHelper.FIELD_ORDER_STATUS, order.getOrder_status());

        db.insert(DBHelper.TABLE_ORDER, null, cv );

        db.close();
    }

    public ArrayList<OrderList> getOrderData(String orderCode) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_subscription = "SELECT *" +
                " FROM " + DBHelper.TABLE_ORDER + " t " +
                " WHERE t. " + DBHelper.FIELD_ORDER_CODE + " = \"" + orderCode + "\"";

        Cursor cursor = db.rawQuery(get_subscription, null);
        cursor.moveToFirst();

        ArrayList<OrderList> Order_List = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                String order_id = cursor.getString(cursor.getColumnIndex(String.valueOf(DBHelper.FIELD_ORDER_ID)));
                int order_user_id = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_ORDER_USER_ID));
                String order_code = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ORDER_CODE));
                String order_menu_name = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ORDER_MENU_NAME));
                String order_menu_price = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ORDER_MENU_PRICE));
                String order_transaction_date = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ORDER_TRANSACTION_DATE));
                String order_status = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ORDER_STATUS));
                Order_List.add(new OrderList(order_id, order_user_id, order_code,  order_menu_name, order_menu_price,
                        order_transaction_date, order_status));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return Order_List;
    }

    public ArrayList<OrderList> ViewAllData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_order = "SELECT DISTINCT order_code,order_transaction_date,order_status " +
                " FROM " + DBHelper.TABLE_ORDER ;

        Cursor cursor = db.rawQuery(get_order, null);
        cursor.moveToFirst();
        ArrayList<OrderList> orderLists = null;
        if (cursor.getCount() > 0) {
            orderLists = new ArrayList<>();
            while (!cursor.isAfterLast()) {
                String order_code = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ORDER_CODE));
                String order_transaction_date = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ORDER_TRANSACTION_DATE));
                String order_status = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_ORDER_STATUS));
                orderLists.add(new OrderList(order_code, order_transaction_date, order_status));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return orderLists;
    }

}
