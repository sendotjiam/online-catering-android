package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AllMenuPage extends AppCompatActivity {

    ImageButton btnAddMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu_page);

        getSupportActionBar().hide();

        btnAddMenu = findViewById(R.id.btn_add_menu);

        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(AllMenuPage.this, AddMenuPage.class);

                startActivity(addIntent);
            }
        });
    }
}