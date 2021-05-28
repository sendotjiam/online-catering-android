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
    int total;

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
        String orderList = intent.getStringExtra("orderList");

        foodlist = orderDB.getOrderData(orderList);

        ordercode.setText(foodlist.get(0).getOrder_code());
        deliverydate.setText(foodlist.get(0).getOrder_transaction_date());
        status.setText(foodlist.get(0).getOrder_status());

        for(int i = 0; i < foodlist.size() ; i++){
            total = total + Integer.parseInt(foodlist.get(i).getOrder_menu_price());
        }
        totalorder.setText(String.valueOf(total));

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