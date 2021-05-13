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
import com.sendo.onlinecatering.R;

import java.util.ArrayList;

public class CustomerOrder extends AppCompatActivity {

    BottomNavigationView nav_admin;
    RecyclerView order_list;
    ArrayList<OrderList> list =new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        getSupportActionBar().hide();

        order_list = findViewById(R.id.orderlist);

        navigation_bar();

        OrderList list1 =new OrderList("C2G3A", "3/25/2021", "Paid");
        list.add(list1);
        OrderList list2 =new OrderList("C2G3B", "3/27/2021", "Paid");
        list.add(list2);
        OrderList list3 =new OrderList("C2G3C", "4/27/2021", "Unpaid");
        list.add(list3);
        OrderList list4 =new OrderList("C2G3C", "4/27/2021", "Unpaid");
        list.add(list3);
        OrderList list5 =new OrderList("C2G3C", "4/27/2021", "Unpaid");
        list.add(list3);
        OrderList list6 =new OrderList("C2G3C", "4/27/2021", "Unpaid");
        list.add(list3);

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
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                if (item.getItemId() == R.id.menu_menus) {
                    Intent intent = new Intent(CustomerOrder.this, ChatAdmin.class);
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