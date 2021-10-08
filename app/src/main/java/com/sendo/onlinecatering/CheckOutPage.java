package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaSession2Service;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sendo.onlinecatering.activities.CartActivity;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import Admin.OrderDB;
import Admin.OrderList;

public class CheckOutPage extends AppCompatActivity {
    TextView olshopcash;
    TextView totalpayment;
    Button pay;
    RecyclerView item_view;
    ArrayList<CheckOutDetail> checkOutDetails = new ArrayList<>();
    CheckOutDetailAdapter checkOutDetailAdapter;
    UsersDB usersDB;
    MenusDB menusDB;
    CartDB cartDB;
    OrderDB orderDB;
    Users users;
    Menus menus;
    Cart cart;
    int userId = 0;
    int totalpembayaran = 0;
    ArrayList<Menus> menus1 = new ArrayList<>();
    ArrayList<FnBDetail> menus2= new ArrayList<>();
    NumberFormat formatter = new DecimalFormat("#,###");

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private CollectionReference ordersReference, cartReference;

    String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_page);

        orderId = getIntent().getStringExtra("OrderId");

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        ordersReference = mFirestore.collection("Orders");
        cartReference = ordersReference.document(orderId).collection("Cart");

        totalpayment = findViewById(R.id.co_totalpayment);
        pay = findViewById(R.id.btn_pay);

        item_view = findViewById(R.id.rv_checkout);

        checkOutDetailAdapter = new CheckOutDetailAdapter(this);
        checkOutDetailAdapter.setMenus(menus1);

        item_view.setAdapter(checkOutDetailAdapter);
        item_view.setLayoutManager(new LinearLayoutManager(this));

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay(view);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        cartReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(DocumentSnapshot document : task.getResult().getDocuments()){
                        Menus menu = document.toObject(Menus.class);

                        menus1.add(menu);
                        checkOutDetailAdapter.notifyItemInserted(menus1.size() - 1);

                        totalpembayaran += menu.getMenu_price();

                        String tampung = formatter.format(totalpembayaran);
                        totalpayment.setText("Rp." + tampung + ",00");
                    }
                } else {
                    Toast.makeText(CheckOutPage.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void backtocart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("OrderId", orderId);
        startActivity(intent);
        finish();
    }

    public void pay(View view) {
        Intent intent = new Intent(getApplicationContext(), InvoicePage.class);
        intent.putExtra("OrderId", orderId);
        startActivity(intent);
        finish();
    }

}