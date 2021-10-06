package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sendo.onlinecatering.activities.MainActivity;

public class ChooseModeActivity extends AppCompatActivity {

    Button btnToAdmin, btnToOrdering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        btnToAdmin = findViewById(R.id.btnToAdmin);
        btnToOrdering = findViewById(R.id.btnToOrdering);

        btnToAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adminIntent = new Intent(ChooseModeActivity.this, AllMenuPage.class);
                startActivity(adminIntent);
            }
        });

        btnToOrdering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent homeIntent = new Intent(this, MainActivity.class);
                intent.putExtra("USERIDLOGINTOHOME", user_id);
                startActivity(homeIntent);*/
            }
        });
    }
}