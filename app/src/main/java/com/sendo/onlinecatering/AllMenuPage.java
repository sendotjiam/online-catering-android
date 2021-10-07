package com.sendo.onlinecatering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

import Admin.CustomerOrder;

public class AllMenuPage extends AppCompatActivity {

    ImageButton btnAddMenu, btn_order, btnLogout;
    RecyclerView rlMenuList;

    ArrayList<Menus> menuArrayList = new ArrayList<Menus>();
    AllMenuAdapter menuAdapter;
    int userid = 0;

    MenusDB menusDB = new MenusDB(AllMenuPage.this);

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private CollectionReference menusReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu_page);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        menusReference = mFirestore.collection("Menus");

        btnAddMenu = findViewById(R.id.btn_add_menu);
        rlMenuList = findViewById(R.id.rv_menu_list);
        btn_order = findViewById(R.id.btn_order);
        btnLogout = findViewById(R.id.btn_logout);

        rlMenuList.setLayoutManager(new LinearLayoutManager(this));

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderIntent();
            }
        });

        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(AllMenuPage.this, AddMenuPage.class);

                startActivity(addIntent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(AllMenuPage.this, LoginActivity.class);
                startActivity(logoutIntent);
                finish();
            }
        });

//        menuArrayList = menusDB.getMenus();

        menuAdapter = new AllMenuAdapter(AllMenuPage.this, menuArrayList);
        rlMenuList.setAdapter(menuAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        menuArrayList.clear();
        menuAdapter.setMenuArrayList(menuArrayList);

        menusReference.whereEqualTo("user_id", mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(DocumentSnapshot document : task.getResult().getDocuments()){
                        Menus menu = document.toObject(Menus.class);

                        menuArrayList.add(menu);
                        menuAdapter.notifyItemInserted(menuArrayList.size() - 1);
                    }

//                    menuAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(AllMenuPage.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void orderIntent() {
        Intent addIntent = new Intent(AllMenuPage.this, CustomerOrder.class);
        addIntent.putExtra("userid", userid);
        startActivity(addIntent);
    }
}