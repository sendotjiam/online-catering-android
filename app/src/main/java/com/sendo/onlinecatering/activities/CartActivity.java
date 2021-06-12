package com.sendo.onlinecatering.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendo.onlinecatering.CartAdapter;
import com.sendo.onlinecatering.CartDB;
import com.sendo.onlinecatering.Menus;
import com.sendo.onlinecatering.ProfilePage;
import com.sendo.onlinecatering.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    int userId = 0;

    RecyclerView rvCart;
    ArrayList<Menus> menuList;
    CartAdapter cartAdapter;
    CartDB cartDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartDB = new CartDB(this);
        Intent intent = getIntent();
        int useridhome_cart = intent.getIntExtra("USERIDHOMETOCART", 0);
        int useridprofile_cart = intent.getIntExtra("USERIDPROFILETOCART", 0);
        int useridcheckout_cart = intent.getIntExtra("USERIDCHECKOUTTOCART", 0);

        if(useridhome_cart > userId){
            userId = useridhome_cart;
        }
        if(useridprofile_cart > userId){
            userId = useridprofile_cart;
        }
        if(useridcheckout_cart > userId){
            userId = useridcheckout_cart;
        }

        menuList =  cartDB.getMenu(userId);

        rvCart = findViewById(R.id.rv_cart);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this, menuList, cartDB);
        rvCart.setAdapter(cartAdapter);

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
                    intent.putExtra("USERIDCARTTOHOME", userId);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.menu_profiles) {
                    Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                    intent.putExtra("USERIDCARTTOPROFILE", userId);
                    startActivity(intent);
                    finish();
                    return true;
                } else {
                    return true;
                }
            }
        });
    }
}