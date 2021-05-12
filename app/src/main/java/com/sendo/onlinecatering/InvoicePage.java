package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class InvoicePage extends AppCompatActivity {
    RecyclerView fnbview;
    ArrayList<FnBDetail> fnBDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_page);

        getSupportActionBar().hide();

        fnbview = findViewById(R.id.rv_fnb);



        fnBDetails.add(new FnBDetail("Ayam Goreng 50 pcs", "Rp500.000,00"));
        fnBDetails.add(new FnBDetail("Ikan Goreng 50 pcs", "Rp300.000,00"));
        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));

        FnBDetailAdapter fnBDetailAdapter = new FnBDetailAdapter(this);
        fnBDetailAdapter.setFnBDetails(fnBDetails);

        fnbview.setAdapter(fnBDetailAdapter);
        fnbview.setLayoutManager(new LinearLayoutManager(this));

    }
}