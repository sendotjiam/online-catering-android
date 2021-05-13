package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerOrder extends AppCompatActivity {

    BottomNavigationView nav_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        getSupportActionBar().hide();

        navigationbar();
    }


    void navigationbar() {
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