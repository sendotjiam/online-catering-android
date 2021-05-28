package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CheckOutPage extends AppCompatActivity {
    TextView olshopcash;
    TextView totalpayment;
    Button pay;
    RecyclerView item_view;
    ArrayList<CheckOutDetail> checkOutDetails = new ArrayList<>();
    UsersDB usersDB;
    MenusDB menusDB;
    CartDB cartDB;
    Users users;
    Menus menus;
    Cart cart;
    NumberFormat formatter = new DecimalFormat("#,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_page);
        olshopcash = findViewById(R.id.co_olcash);
        totalpayment = findViewById(R.id.co_totalpayment);
        pay = findViewById(R.id.btn_pay);

        usersDB = new UsersDB(this);
        menusDB = new MenusDB(this);
        cartDB = new CartDB(this);

//        /data/media/0/DCIM/ayamgoreng.PNG
//        /data/media/0/DCIM/ikangoreng.PNG
//        cart = new Cart();
//        cart.setUser_id(1);
//        cart.setMenu_id(2);
//
//        cartDB.insertCart(cart);

        item_view = findViewById(R.id.rv_checkout);


//        CheckOutDetailAdapter checkOutDetailAdapter = new CheckOutDetailAdapter(this);
//        checkOutDetailAdapter.setMenus(checkOutDetails);
//
//        item_view.setAdapter(checkOutDetailAdapter);
//        item_view.setLayoutManager(new LinearLayoutManager(this));

        //ingat ganti 1 nya jadi user_id juga
        users = usersDB.getUser(1);

        String formattampung = formatter.format(users.getWallet());
        olshopcash.setText("Rp." + formattampung + ",00");
    }

    public void pay(View view) {
    }
}