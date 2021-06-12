package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import Admin.ChatAdmin;
import Admin.CustomerOrder;

public class AllMenuPage extends AppCompatActivity {

    ImageButton btnAddMenu, btn_order;
    RecyclerView rlMenuList;

    ArrayList<Menus> menuArrayList = new ArrayList<Menus>();
    AllMenuAdapter menuAdapter;
    int userid = 0;

    MenusDB menusDB = new MenusDB(AllMenuPage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu_page);

        navigation_bar();

        btnAddMenu = findViewById(R.id.btn_add_menu);
        rlMenuList = findViewById(R.id.rv_menu_list);
        btn_order = findViewById(R.id.btn_order);

        rlMenuList.setLayoutManager(new LinearLayoutManager(this));

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderIntent();
            }
        });

        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(AllMenuPage.this, AddMenuPage.class);

                startActivity(addIntent);
            }
        });

//        menuArrayList = menusDB.getMenus();

        menuAdapter = new AllMenuAdapter(AllMenuPage.this, menuArrayList);
        rlMenuList.setAdapter(menuAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        menuAdapter.setMenuArrayList(menusDB.getMenus());
    }

    void navigation_bar() {
        BottomNavigationView nav_admin = findViewById(R.id.nav_admin);

        nav_admin.setSelectedItemId(R.id.menu_menus);
        nav_admin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                if (item.getItemId() == R.id.menu_menus) {
                    return true;
                }   else if (item.getItemId() == R.id.menu_order) {
                    Toast.makeText(AllMenuPage.this, "orders", Toast.LENGTH_SHORT).show();

                    Intent orderIntent = new Intent(AllMenuPage.this, CustomerOrder.class);
                    orderIntent.putExtra("userid", userid);
                    startActivity(orderIntent);

                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    Toast.makeText(AllMenuPage.this, "profile", Toast.LENGTH_SHORT).show();

                    //PROFILE
                    /*Intent profileIntent = new Intent(AllMenuPage.this, CustomerOrder.class);
                    profileIntent.putExtra("userid", userid);
                    startActivity(profileIntent);*/

                    return true;
                } else {


                    return true;
                }
            }
        });
    }

    void orderIntent() {
        Intent addIntent = new Intent(AllMenuPage.this, CustomerOrder.class);
        addIntent.putExtra("userid", userid);
        startActivity(addIntent);
    }
}