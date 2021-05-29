package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sendo.onlinecatering.activities.CartActivity;

import java.util.ArrayList;

import Admin.CustomerOrder;

public class InvoicePage extends AppCompatActivity {
    RecyclerView fnbview;
    TextView transactiondate;
    TextView order_code;
    ArrayList<FnBDetail> fnBDetails = new ArrayList<>();
    ArrayList<Menus> menus = new ArrayList<>();
    String transactiondate1;
    String order_code1;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_page);
        transactiondate = findViewById(R.id.transactiondate);
        order_code = findViewById(R.id.ordercode);
        fnbview = findViewById(R.id.rv_fnb);

         /*
        INGAT SEMUA 1 GANTI JADI user_id
         */

        Intent intent = getIntent();
        user_id = intent.getIntExtra("USERIDFROMCHECKOUT", 0);
        transactiondate1 = intent.getStringExtra("TRANSACTIONDATE");
        order_code1 = intent.getStringExtra("ORDERCODE");
        menus = intent.getParcelableArrayListExtra("MENU");

        Toast.makeText(this, user_id+ "", Toast.LENGTH_SHORT).show();

        if(menus != null) {
            for (int i = 0; i < menus.size(); i++) {
                fnBDetails.add(new FnBDetail(menus.get(i).getMenu_name(), String.valueOf(menus.get(i).getMenu_price())));
            }
        }

        transactiondate.setText(transactiondate1);
        order_code.setText(order_code1);

//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));
//        fnBDetails.add(new FnBDetail("Sosis Goreng 50 pcs", "Rp200.000,00"));

        FnBDetailAdapter fnBDetailAdapter = new FnBDetailAdapter(this);
        fnBDetailAdapter.setFnBDetails(fnBDetails);

        fnbview.setAdapter(fnBDetailAdapter);
        fnbview.setLayoutManager(new LinearLayoutManager(this));

    }

    public void backtohome(View view) {
        Intent intent = new Intent(this, CustomerOrder.class);
        //ingat ganti 1 nya jdi user_id
        intent.putExtra("USERIDFROMINVOICE", 1);
        startActivity(intent);
        finish();
    }
}