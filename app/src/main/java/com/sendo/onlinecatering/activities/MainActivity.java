package com.sendo.onlinecatering.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sendo.onlinecatering.Menus;
import com.sendo.onlinecatering.MenusAdapter;
import com.sendo.onlinecatering.R;
import com.sendo.onlinecatering.Users;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Menus> menus = new ArrayList<>();
    RecyclerView rvMenus;
    MenusAdapter menusAdapter;
    Button btnCancel;
    TextView tvName;
    int userId = 0;
    Users user;

    String orderId;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurUser;
    private FirebaseFirestore mFirestore;
    private CollectionReference menusReference, ordersReference, cartReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderId = getIntent().getStringExtra("OrderId");

        mAuth = FirebaseAuth.getInstance();
        mCurUser = mAuth.getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
        menusReference = mFirestore.collection("Menus");
        ordersReference = mFirestore.collection("Orders");
        cartReference = ordersReference.document(orderId).collection("Cart");

        tvName = findViewById(R.id.tv_name);
        rvMenus = findViewById(R.id.rv_menu);
        btnCancel = findViewById(R.id.btn_cancel);

        tvName.setText(orderId);

        rvMenus.setLayoutManager(new GridLayoutManager(this, 2));
        menusAdapter = new MenusAdapter(this, menus, cartReference);
        rvMenus.setAdapter(menusAdapter);

        ordersReference.document(orderId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> customerMap = documentSnapshot.getData();

                tvName.setText(customerMap.get("name").toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordersReference.document(orderId).delete();
                finish();
            }
        });

        navbar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        menus.clear();

        menusReference.whereEqualTo("user_id", mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(DocumentSnapshot document : task.getResult().getDocuments()){
                        Menus menu = document.toObject(Menus.class);

                        menus.add(menu);
                        menusAdapter.notifyItemInserted(menus.size() - 1);
                    }

//                    menuAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void navbar() {
        BottomNavigationView nav_klient = findViewById(R.id.navbar_klient);
        nav_klient.setSelectedItemId(R.id.menu_home);
        nav_klient.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    intent.putExtra("OrderId", orderId);
                    startActivity(intent);
                    finish();
                    return true;
                } else {
                    return true;
                }
            }
        });
    }
}