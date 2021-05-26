package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        getSupportActionBar().hide();
    }

    public void topup(View view) {
    }

    public void backhome(View view) {
    }

    public void backcart(View view) {
    }
}