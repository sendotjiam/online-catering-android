package Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sendo.onlinecatering.AllMenuPage;
import com.sendo.onlinecatering.Menus;
import com.sendo.onlinecatering.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerOrder extends AppCompatActivity {

    BottomNavigationView nav_admin;
    RecyclerView order_list;
    ArrayList<HashMap<String, String>> list2 = new ArrayList();
    OrderListAdapter orderListAdapter;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private CollectionReference ordersReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        ordersReference = mFirestore.collection("Orders");

        order_list = findViewById(R.id.orderlist);

        orderListAdapter = new OrderListAdapter(CustomerOrder.this, list2);
        order_list.setAdapter(orderListAdapter);
        order_list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        ordersReference.whereEqualTo("userId", mAuth.getUid()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot document : queryDocumentSnapshots.getDocuments()){
                    Map documentMap = document.getData();

                    HashMap<String, String> orderMap = new HashMap<>();
                    orderMap.put("orderCode", document.getId());
                    orderMap.put("date", documentMap.get("date").toString());
                    orderMap.put("type", (Boolean)documentMap.get("DineIn") ? "Dine In (" + document.get("tableNumber")+ ")" : "Take away" );

                    list2.add(orderMap);
                    orderListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void Icon_Back(View view) {
        finish();
    }
}