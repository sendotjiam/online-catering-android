package com.sendo.onlinecatering.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendo.onlinecatering.Menus;
import com.sendo.onlinecatering.MenusAdapter;
import com.sendo.onlinecatering.MenusDB;
import com.sendo.onlinecatering.ProfilePage;
import com.sendo.onlinecatering.R;
import com.sendo.onlinecatering.Users;
import com.sendo.onlinecatering.UsersDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Menus> menus;
    RecyclerView rvMenus;
    MenusAdapter menusAdapter;
    TextView tvName;
    int userId = 0;
    Users user;
    UsersDB usersDB;
    MenusDB menusDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.tv_name);
        usersDB = new UsersDB(this);
        menusDB = new MenusDB(this);
        menus = menusDB.getMenus();

        Intent intent = getIntent();
        int useridlogin_home = intent.getIntExtra("USERIDLOGINTOHOME", 0);
        int useridcart_home = intent.getIntExtra("USERIDCARTTOHOME", 0);
        int useridprofile_home = intent.getIntExtra("USERIDPROFILETOHOME", 0);
        int useridinvoice_home = intent.getIntExtra("USERIDFROMINVOICETOHOME", 0);

        if(useridlogin_home > userId){
            userId = useridlogin_home;
        }
        if(useridcart_home > userId){
            userId = useridcart_home;
        }
        if(useridprofile_home > userId){
            userId = useridprofile_home;
        }
        if(useridinvoice_home > userId){
            userId = useridinvoice_home;
        }

        user = usersDB.getUser(userId);
        tvName = findViewById(R.id.tv_name);
        tvName.setText(user.getUsername());

        rvMenus = findViewById(R.id.rv_menu);
        rvMenus.setLayoutManager(new GridLayoutManager(this, 2));
        menusAdapter = new MenusAdapter(this, menus, userId);
//        menusAdapter.notifyDataSetChanged();
        rvMenus.setAdapter(menusAdapter);

        navbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        menusAdapter.setMenuArrayList(menusDB.getMenus());
    }

    void navbar() {
        BottomNavigationView nav_klient = findViewById(R.id.navbar_klient);
        nav_klient.setSelectedItemId(R.id.menu_home);
        nav_klient.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    intent.putExtra("USERIDHOMETOCART", userId);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.menu_profiles) {
                    Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                    intent.putExtra("USERIDHOMETOPROFILE", userId);
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