package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CheckOutPage extends AppCompatActivity {
    RecyclerView item_view;
    ArrayList<CheckOutDetail> checkOutDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_page);

        getSupportActionBar().hide();

        item_view = findViewById(R.id.rv_checkout);

        checkOutDetails.add(new CheckOutDetail(R.drawable.ayamgoreng,
                "Ayam Goreng Lengkuas",
                "Ayam goreng dengan menggunakan bumbu lengkuas gurih dan sehat",
                "Ayam Goreng Lengkuas 50 pcs untuk nikahan anak",
                "13 Mei 2021",
                "Rp500.000,00"));

        checkOutDetails.add(new CheckOutDetail(R.drawable.ikangoreng,
                "Ikan Goreng Lengkuas",
                "Ikan goreng dengan menggunakan bumbu lengkuas gurih dan sehat",
                "Ikan Goreng Lengkuas 50 pcs untuk nikahan anak",
                "13 Mei 2021",
                "Rp600.000,00"));

        checkOutDetails.add(new CheckOutDetail(R.drawable.ayamgoreng,
                "Ayam Goreng Lengkuas",
                "Ayam goreng dengan menggunakan bumbu lengkuas gurih dan sehat",
                "Ayam Goreng Lengkuas 50 pcs untuk nikahan anak",
                "13 Mei 2021",
                "Rp500.000,00"));

        checkOutDetails.add(new CheckOutDetail(R.drawable.ikangoreng,
                "Ikan Goreng Lengkuas",
                "Ikan goreng dengan menggunakan bumbu lengkuas gurih dan sehat",
                "Ikan Goreng Lengkuas 50 pcs untuk nikahan anak",
                "13 Mei 2021",
                "Rp600.000,00"));

        CheckOutDetailAdapter checkOutDetailAdapter = new CheckOutDetailAdapter(this);
        checkOutDetailAdapter.setCheckOutDetails(checkOutDetails);

        item_view.setAdapter(checkOutDetailAdapter);
        item_view.setLayoutManager(new LinearLayoutManager(this));
    }
}