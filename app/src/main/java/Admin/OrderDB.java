package Admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sendo.onlinecatering.Order;
import com.sendo.onlinecatering.UserDBHelper;
import com.sendo.onlinecatering.Users;

import java.util.ArrayList;

public class OrderDB {

    private UserDBHelper dbHelper;

    public OrderDB(Context ctx){
        dbHelper = new UserDBHelper(ctx);
    }

    public void insertUsers(Order order){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UserDBHelper.FIELD_ORDER_ID, order.getOrder_id());
        cv.put(UserDBHelper.FIELD_ORDER_USER_ID, order.getUser_id());
        cv.put(UserDBHelper.FIELD_ORDER_CODE, order.getOrder_code());
        cv.put(UserDBHelper.FIELD_ORDER_MENU_NAME, order.getMenu_name());
        cv.put(UserDBHelper.FIELD_ORDER_MENU_PRICE, order.getMenu_price());
        cv.put(UserDBHelper.FIELD_ORDER_TRANSACTION_DATE, order.getTransaction_date());
        cv.put(UserDBHelper.FIELD_ORDER_STATUS, order.getStatus());

        db.insert(UserDBHelper.TABLE_ORDER, null, cv );

        db.close();
    }

    public ArrayList<OrderList> getOrderData(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_subscription = "SELECT *" +
                " FROM " + UserDBHelper.TABLE_ORDER + " t " +
                " WHERE t. " + UserDBHelper.FIELD_ORDER_USER_ID + " = " + id;

        Cursor cursor = db.rawQuery(get_subscription, null);

        ArrayList<OrderList> Order_List = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                String order_id = cursor.getString(cursor.getColumnIndex(UserDBHelper.FIELD_ORDER_ID));
                int order_user_id = cursor.getInt(cursor.getColumnIndex(UserDBHelper.FIELD_ORDER_USER_ID));
                String order_code = cursor.getString(cursor.getColumnIndex(UserDBHelper.FIELD_ORDER_CODE));
                String order_menu_name = cursor.getString(cursor.getColumnIndex(UserDBHelper.FIELD_ORDER_MENU_NAME));
                String order_menu_price = cursor.getString(cursor.getColumnIndex(UserDBHelper.FIELD_ORDER_MENU_PRICE));
                String order_transaction_date = cursor.getString(cursor.getColumnIndex(UserDBHelper.FIELD_ORDER_TRANSACTION_DATE));
                String order_status = cursor.getString(cursor.getColumnIndex(UserDBHelper.FIELD_ORDER_STATUS));
                Order_List.add(new OrderList(order_id, order_user_id, order_code,  order_menu_name, order_menu_price,
                        order_transaction_date, order_status));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return Order_List;
    }
}
