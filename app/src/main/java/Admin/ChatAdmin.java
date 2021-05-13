package Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sendo.onlinecatering.R;

public class ChatAdmin extends AppCompatActivity {
    BottomNavigationView nav_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_admin);

        getSupportActionBar().hide();

        navigation_bar();
    }

    void navigation_bar() {
        BottomNavigationView nav_admin = findViewById(R.id.nav_admin);

        nav_admin.setSelectedItemId(R.id.menu_chat);
        nav_admin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                if (item.getItemId() == R.id.menu_menus) {
                    Intent intent = new Intent(ChatAdmin.this, CustomerOrder.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_chat) {
                    return true;
                } else if (item.getItemId() == R.id.menu_order) {
                    Intent intent = new Intent(ChatAdmin.this, CustomerOrder.class);
                    startActivity(intent);
                    return true;
                } else {
                    Intent intent = new Intent(ChatAdmin.this, CustomerOrder.class);
                    startActivity(intent);
                    return true;
                }
            }
        });
    }
}