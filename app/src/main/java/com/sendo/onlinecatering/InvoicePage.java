package com.sendo.onlinecatering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sendo.onlinecatering.activities.CartActivity;
import com.sendo.onlinecatering.activities.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Admin.CustomerOrder;

public class InvoicePage extends AppCompatActivity {
    RecyclerView fnbview;
    TextView transactiondate;
    TextView order_code;
    ArrayList<FnBDetail> fnBDetails = new ArrayList<>();
    FnBDetailAdapter fnBDetailAdapter;
    String transactiondate1;
    String order_code1;
    int user_id;

    private FirebaseFirestore mFirestore;
    private CollectionReference ordersReference, cartReference;

    String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_page);

        Intent intent = getIntent();
        orderId = intent.getStringExtra("OrderId");

        mFirestore = FirebaseFirestore.getInstance();
        ordersReference = mFirestore.collection("Orders");
        cartReference = ordersReference.document(orderId).collection("Cart");

        transactiondate = findViewById(R.id.transactiondate);
        order_code = findViewById(R.id.ordercode);
        fnbview = findViewById(R.id.rv_fnb);

        fnBDetailAdapter = new FnBDetailAdapter(this, fnBDetails);

        fnbview.setAdapter(fnBDetailAdapter);
        fnbview.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();

        ordersReference.document(orderId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map document = documentSnapshot.getData();

                transactiondate.setText(document.get("date").toString());
                order_code.setText(documentSnapshot.getId());

                cartReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot document : queryDocumentSnapshots.getDocuments()){
                            Menus menu = document.toObject(Menus.class);

                            FnBDetail fnBDetail = new FnBDetail();
                            fnBDetail.setFnbname(menu.getMenu_name());
                            fnBDetail.setFnbprice(menu.getMenu_price());

                            fnBDetails.add(fnBDetail);
                            fnBDetailAdapter.notifyItemInserted(fnBDetails.size() - 1);
                        }
                    }
                });
//                fnBDetailAdapter.setFnBDetails(fnBDetails);
            }
        });
    }

    public void backtohome(View view) {

        Map<String,Object> updates = new HashMap<>();
        updates.put("submitted", true);
        updates.put("finish", false);

        ordersReference.document(orderId).update(updates);

        finish();
    }
}