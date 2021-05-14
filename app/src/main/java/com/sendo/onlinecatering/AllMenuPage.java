package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class AllMenuPage extends AppCompatActivity {

    ImageButton btnAddMenu;
    RecyclerView rlMenuList;

    AllMenuAdapter menuAdapter;
    ArrayList<Menu> menuArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu_page);

        getSupportActionBar().hide();

        btnAddMenu = findViewById(R.id.btn_add_menu);
        rlMenuList = findViewById(R.id.rv_menu_list);

        rlMenuList.setLayoutManager(new LinearLayoutManager(this));

        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(AllMenuPage.this, AddMenuPage.class);

                startActivity(addIntent);
            }
        });

        menuArrayList.add(new Menu("Ayam Goreng 50pcs", 500000));
        menuArrayList.add(new Menu("Ikan Goreng 30pcs", 300000));
        menuArrayList.add(new Menu("Sosis Goreng 100pcs", 200000));

        menuAdapter = new AllMenuAdapter(AllMenuPage.this, menuArrayList);
        rlMenuList.setAdapter(menuAdapter);

    }
}