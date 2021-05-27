package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CheckOutPage extends AppCompatActivity {
    TextView olshopcash;
    TextView totalpayment;
    Button pay;
    RecyclerView item_view;
    ArrayList<CheckOutDetail> checkOutDetails = new ArrayList<>();
    UsersDB usersDB;
    Users users;
    NumberFormat formatter = new DecimalFormat("#,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_page);
        olshopcash = findViewById(R.id.co_olcash);
        totalpayment = findViewById(R.id.co_totalpayment);
        pay = findViewById(R.id.btn_pay);

        usersDB = new UsersDB(this);

        item_view = findViewById(R.id.rv_checkout);

        checkOutDetails.add(new CheckOutDetail(R.drawable.ayamgoreng,
                "Ayam Goreng Lengkuas",
                "Ayam goreng dengan menggunakan bumbu lengkuas gurih dan sehat",
                "Ayam Goreng Lengkuas",
                "Rp500.000,00"));

        checkOutDetails.add(new CheckOutDetail(R.drawable.ikangoreng,
                "Ikan Goreng Lengkuas",
                "Ikan goreng dengan menggunakan bumbu lengkuas gurih dan sehat",
                "Ikan Goreng Lengkuas",
                "Rp600.000,00"));

        checkOutDetails.add(new CheckOutDetail(R.drawable.ayamgoreng,
                "Ayam Goreng Lengkuas",
                "Ayam goreng dengan menggunakan bumbu lengkuas gurih dan sehat",
                "Ayam Goreng Lengkuas",
                "Rp500.000,00"));

        checkOutDetails.add(new CheckOutDetail(R.drawable.ikangoreng,
                "Ikan Goreng Lengkuas",
                "Ikan goreng dengan menggunakan bumbu lengkuas gurih dan sehat",
                "Ikan Goreng Lengkuas",
                "Rp600.000,00"));

        CheckOutDetailAdapter checkOutDetailAdapter = new CheckOutDetailAdapter(this);
        checkOutDetailAdapter.setCheckOutDetails(checkOutDetails);

        item_view.setAdapter(checkOutDetailAdapter);
        item_view.setLayoutManager(new LinearLayoutManager(this));

        //ingat ganti 1 nya jadi user_id juga
        users = usersDB.getUser(1);

        String formattampung = formatter.format(users.getWallet());
        olshopcash.setText("Rp." + formattampung + ",00");
    }

    public void pay(View view) {
    }
}