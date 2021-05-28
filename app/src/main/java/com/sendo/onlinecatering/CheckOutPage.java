package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import Admin.OrderList;

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
    int totalpembayaran = 0;
    ArrayList<Menus> menus1 = new ArrayList<>();
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
//        /storage/emulated/0/DCIM/ayamgoreng.PNG
//        /storage/emulated/0/DCIM/ikangoreng.PNG

//        users = new Users();
//        users.setUsername("Hendry Gunawan");
//        users.setPassword("hendry123");
//        users.setGender("Male");
//        users.setPhone("081276652918");
//        users.setDateOfBirth("3/4/2001");
//        users.setWallet(3000000);
//        usersDB.insertUsers(users);

//        menus = new Menus();
//        menus.setMenu_name("Ayam Goreng Lengkuas");
//        menus.setMenu_description("Ayam goreng dengan bumbu lengkuas yang gurih");
//        menus.setMenu_img_path("/storage/emulated/0/DCIM/ayamgoreng.PNG");
//        menus.setMenu_price(300000);
//        menusDB.insertMenus(menus);
//
//        cart = new Cart();
//        cart.setUser_id(1);
//        cart.setMenu_id(2);
//        cartDB.insertCart(cart);

        //ingat ganti 1 nya jadi user_id juga;
        menus1 = cartDB.getMenu(1);

        for(int i = 0; i < menus1.size(); i++){
            totalpembayaran += menus1.get(i).getMenu_price();
        }
        String tampung = formatter.format(totalpembayaran);
        totalpayment.setText("Rp." + tampung + ",00");

        item_view = findViewById(R.id.rv_checkout);


        CheckOutDetailAdapter checkOutDetailAdapter = new CheckOutDetailAdapter(this);
        checkOutDetailAdapter.setMenus(menus1);
//
        item_view.setAdapter(checkOutDetailAdapter);
        item_view.setLayoutManager(new LinearLayoutManager(this));

        //ingat ganti 1 nya jadi user_id juga
        users = usersDB.getUser(1);

        String formattampung = formatter.format(users.getWallet());
        olshopcash.setText("Rp." + formattampung + ",00");
    }

    public void backtocart(View view) {
    }

    public void pay(View view) {
        if(users.getWallet() < totalpembayaran){
            Toast.makeText(this, "You dont have enough olshop cash", Toast.LENGTH_SHORT).show();
        }
        else{
            //ingat ganti 1 ke user_id
            usersDB.minusNominal(users, 1, totalpembayaran);
            cartDB.deleteCart(1);
            RandomString randomString = new RandomString(5, ThreadLocalRandom.current());
            String transactiondate = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault()).format(new Date());
            for(int i = 0; i < menus1.size(); i++){
                OrderList orderList = new OrderList();
                orderList.setOrder_user_id(1);
                orderList.setOrder_code(String.valueOf(randomString));
                orderList.setOrder_menu_name(menus1.get(i).getMenu_name());
                orderList.setOrder_menu_price(String.valueOf(menus1.get(i).getMenu_price()));
                orderList.setOrder_transaction_date(transactiondate);
                orderList.setOrder_status("Paid");
            }
        }
    }


}