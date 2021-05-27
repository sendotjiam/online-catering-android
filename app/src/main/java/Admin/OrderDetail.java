package Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sendo.onlinecatering.R;

import java.util.ArrayList;

public class OrderDetail extends AppCompatActivity {

    ImageButton ic_back;
    TextView ordercode, deliverydate, status;
    TextView totalorder;
    RecyclerView food_order;
    ArrayList<OrderList> foodlist = new ArrayList<>();
    OrderDB orderDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getSupportActionBar().hide();

        ic_back = findViewById(R.id.ic_back);
        ordercode = findViewById(R.id.ordercode);
        deliverydate = findViewById(R.id.deliverydate);
        status = findViewById(R.id.status);
        food_order = findViewById(R.id.food_order);
        totalorder = findViewById(R.id.totalorder);
        orderDB = new OrderDB(this);

        Intent intent = getIntent();
        int orderList = intent.getIntExtra("orderList", 0);

        foodlist = orderDB.getOrderData(orderList);

        OrderDetailListAdapter orderDetailListAdapter = new OrderDetailListAdapter();
        orderDetailListAdapter.setArrayListdata(foodlist);
        food_order.setAdapter(orderDetailListAdapter);
        food_order.setLayoutManager(new LinearLayoutManager(this));
    }

    public void Icon_Back(View view) {
        Intent intent = new Intent(OrderDetail.this, CustomerOrder.class);
        startActivity(intent);
    }

    public void Button_Back(View view) {
        Intent intent = new Intent(OrderDetail.this, CustomerOrder.class);
        startActivity(intent);
    }
}