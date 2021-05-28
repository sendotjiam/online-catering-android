package Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendo.onlinecatering.AllMenuPage;
import com.sendo.onlinecatering.R;

import java.util.ArrayList;

public class CustomerOrder extends AppCompatActivity {

    BottomNavigationView nav_admin;
    RecyclerView order_list;
    ArrayList<OrderList> list = new ArrayList();
    ArrayList<OrderList> list2 = new ArrayList();
    ArrayList<OrderList> listResult = new ArrayList();

    OrderDB orderDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);

        order_list = findViewById(R.id.orderlist);
        orderDB = new OrderDB(this);

        navigation_bar();

        list = orderDB.ViewAllData();
        list2 = orderDB.ViewAllData();

        for (int i = 0; i < list.size(); i++) {
            for (int x = 0; x < list.size(); x++) {
                if (listResult.get(x).getOrder_code() != null) {
                    if (!list.get(i).getOrder_code().equals(listResult.get(x).getOrder_code())) {
                        OrderList orderlist = new OrderList(list.get(i).getOrder_id(), list.get(i).getOrder_user_id(),
                                list.get(i).getOrder_code(), list.get(i).getOrder_menu_name(), list.get(i).getOrder_menu_price(),
                                list.get(i).getOrder_transaction_date(), list.get(i).getOrder_status());
                        listResult.add(orderlist);
                    }
                }
            }
        }

        OrderListAdapter orderListAdapter = new OrderListAdapter();
        orderListAdapter.setArrayListdata(list);
        order_list.setAdapter(orderListAdapter);
        order_list.setLayoutManager(new LinearLayoutManager(this));

    }


    void navigation_bar() {
        BottomNavigationView nav_admin = findViewById(R.id.nav_admin);

        nav_admin.setSelectedItemId(R.id.menu_order);
        nav_admin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_menus) {
                    Intent intent = new Intent(CustomerOrder.this, AllMenuPage.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_chat) {
                    Intent intent = new Intent(CustomerOrder.this, ChatAdmin.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_order) {
                    return true;
                } else {
                    Intent intent = new Intent(CustomerOrder.this, ChatAdmin.class);
                    startActivity(intent);
                    return true;
                }
            }
        });
    }

}