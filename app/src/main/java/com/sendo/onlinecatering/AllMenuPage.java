package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import Admin.CustomerOrder;

public class AllMenuPage extends AppCompatActivity {

    ImageButton btnAddMenu, btn_order, btnLogout;
    RecyclerView rlMenuList;

    ArrayList<Menus> menuArrayList = new ArrayList<Menus>();
    AllMenuAdapter menuAdapter;
    int userid = 0;

    MenusDB menusDB = new MenusDB(AllMenuPage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu_page);

        btnAddMenu = findViewById(R.id.btn_add_menu);
        rlMenuList = findViewById(R.id.rv_menu_list);
        btn_order = findViewById(R.id.btn_order);
        btnLogout = findViewById(R.id.btn_logout);

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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(AllMenuPage.this, LoginActivity.class);
                startActivity(logoutIntent);
                finish();
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

    void orderIntent() {
        Intent addIntent = new Intent(AllMenuPage.this, CustomerOrder.class);
        addIntent.putExtra("userid", userid);
        startActivity(addIntent);
    }
}