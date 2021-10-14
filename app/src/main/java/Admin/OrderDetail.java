package Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sendo.onlinecatering.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDetail extends AppCompatActivity {

    ImageButton ic_back;
    TextView ordercode, deliverydate, status;
    TextView totalorder;
    RecyclerView food_order;
    ArrayList<HashMap<String, Object>> foodlist = new ArrayList<>();
    OrderDetailListAdapter orderDetailListAdapter;
    int total, userid;

    private FirebaseFirestore mFirestore;
    private CollectionReference ordersReference, cartReference;

    String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        orderId = getIntent().getStringExtra("OrderId");

        mFirestore = FirebaseFirestore.getInstance();
        ordersReference = mFirestore.collection("Orders");
        cartReference = ordersReference.document(orderId).collection("Cart");

        ic_back = findViewById(R.id.ic_back);
        ordercode = findViewById(R.id.ordercode);
        deliverydate = findViewById(R.id.deliverydate);
        status = findViewById(R.id.status);
        food_order = findViewById(R.id.food_order);
        totalorder = findViewById(R.id.totalorder);

//        ordercode.setText(foodlist.get(0).getOrder_code());
//        deliverydate.setText(foodlist.get(0).getOrder_transaction_date());
//        status.setText(foodlist.get(0).getOrder_status());

//        for (int i = 0; i < foodlist.size(); i++) {
//            total = total + Integer.parseInt(foodlist.get(i).getOrder_menu_price());
//        }
//        totalorder.setText(String.valueOf(total));

        orderDetailListAdapter = new OrderDetailListAdapter(OrderDetail.this, foodlist);
        food_order.setAdapter(orderDetailListAdapter);
        food_order.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();

        ordersReference.document(orderId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Map documentMap = documentSnapshot.getData();

                ordercode.setText(documentSnapshot.getId());
                deliverydate.setText(documentMap.get("date").toString());
                status.setText((Boolean)documentMap.get("DineIn") ? "Dine In (" + documentMap.get("tableNumber")+ ")" : "Take away");
            }
        });

        cartReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (DocumentSnapshot documentSnapshot: queryDocumentSnapshots.getDocuments()){
                    HashMap data = (HashMap) documentSnapshot.getData();

                    total += Integer.parseInt(data.get("menu_price").toString());

                    foodlist.add(data);
                    orderDetailListAdapter.notifyItemInserted(foodlist.size() - 1);
                }

                totalorder.setText(String.valueOf(total));
            }
        });
    }

    public void Icon_Back(View view) {
        Intent intent = new Intent(OrderDetail.this, CustomerOrder.class);
        intent.putExtra("userid", userid);
        startActivity(intent);
        finish();
    }
}