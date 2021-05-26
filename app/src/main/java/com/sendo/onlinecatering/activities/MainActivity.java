package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sendo.onlinecatering.R;

import Admin.ChatAdmin;
import Admin.CustomerOrder;

public class MainActivity extends AppCompatActivity {
    Button sementara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void pindah(View view) {
        Intent intent = new Intent(this, ChatAdmin.class);
        startActivity(intent);
    }
}