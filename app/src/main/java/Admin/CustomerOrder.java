package Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendo.onlinecatering.AllMenuPage;
import com.sendo.onlinecatering.R;

import java.util.ArrayList;

public class CustomerOrder extends AppCompatActivity {

    BottomNavigationView nav_admin;
    RecyclerView order_list;
    ArrayList<OrderList> list = new ArrayList();
    ArrayList<OrderList> listResult = new ArrayList<>();

    OrderDB orderDB;
    int userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);

        order_list = findViewById(R.id.orderlist);
        orderDB = new OrderDB(this);

        list = orderDB.ViewAllData();

//        OrderList orderlist = new OrderList(list.get(0).getOrder_id(), list.get(0).getOrder_user_id(),
//                list.get(0).getOrder_code(), list.get(0).getOrder_menu_name(), list.get(0).getOrder_menu_price(),
//                list.get(0).getOrder_transaction_date(), list.get(0).getOrder_status());
//        listResult.add(orderlist);
//
//        if (list.size() > 1) {
//            for (int i = 1; i < list.size(); i++) {
//                for (int x = 0; x < listResult.size(); x++) {
//                    if (!list.get(i).getOrder_code().equals(listResult.get(x).getOrder_code())) {
//                        OrderList orderlist1 = new OrderList(list.get(i).getOrder_id(), list.get(i).getOrder_user_id(),
//                                list.get(i).getOrder_code(), list.get(i).getOrder_menu_name(), list.get(i).getOrder_menu_price(),
//                                list.get(i).getOrder_transaction_date(), list.get(i).getOrder_status());
//                        listResult.add(orderlist1);
//                    }
//                }
//            }
//        }
        Intent intent = getIntent();
        userid = intent.getIntExtra("userid", 0);
        OrderListAdapter orderListAdapter = new OrderListAdapter();
        orderListAdapter.setArrayListdata(list, userid);
        order_list.setAdapter(orderListAdapter);
        order_list.setLayoutManager(new LinearLayoutManager(this));

    }


    public void Icon_Back(View view) {
        Intent intent = new Intent(this, AllMenuPage.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
        finish();
    }
}