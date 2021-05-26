package com.sendo.onlinecatering.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendo.onlinecatering.ProfilePage;
import com.sendo.onlinecatering.R;

public class CartActivity extends AppCompatActivity {
    int user_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

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
                    intent.putExtra("PROFILETOHOME", user_id);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.menu_profiles) {
                    Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                    intent.putExtra("PROFILETOCART", user_id);
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