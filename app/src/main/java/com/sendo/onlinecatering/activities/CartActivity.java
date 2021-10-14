package com.sendo.onlinecatering.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sendo.onlinecatering.CartAdapter;
import com.sendo.onlinecatering.CheckOutPage;
import com.sendo.onlinecatering.Menus;
import com.sendo.onlinecatering.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    int userId = 0;

    RecyclerView rvCart;
    ArrayList<Menus> menuList = new ArrayList<>();
    CartAdapter cartAdapter;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private CollectionReference ordersReference, cartReference;

    String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        orderId = getIntent().getStringExtra("OrderId");

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        ordersReference = mFirestore.collection("Orders");
        cartReference = ordersReference.document(orderId).collection("Cart");

        rvCart = findViewById(R.id.rv_cart);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this, menuList, cartReference);
        rvCart.setAdapter(cartAdapter);

        cartReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(DocumentSnapshot document : task.getResult().getDocuments()){
                        Menus menu = document.toObject(Menus.class);

                        menu.setMenu_id(document.getId());

                        menuList.add(menu);
                        cartAdapter.notifyItemInserted(menuList.size() - 1);
                    }
                } else {
                    Toast.makeText(CartActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                cartAdapter.notifyDataSetChanged();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });

        navbar();
    }

    void navbar() {
        BottomNavigationView nav_klient = findViewById(R.id.navbar_klient);
        nav_klient.setSelectedItemId(R.id.menu_cart);
        nav_klient.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("OrderId", orderId);
                    startActivity(intent);
                    finish();
                    return true;
                } else {
                    return true;
                }
            }
        });
    }

    public void openCheckOutActivity(View view) {
        Intent intent = new Intent(this, CheckOutPage.class);
        intent.putExtra("OrderId", orderId);
        startActivity(intent);
        finish();
    }

}