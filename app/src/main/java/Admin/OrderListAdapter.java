package Admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sendo.onlinecatering.Order;
import com.sendo.onlinecatering.R;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    public static final int REQUEST_CODE_REPLY = 1;

    Context context;
//    ArrayList<OrderList> list = new ArrayList<>();
    ArrayList<HashMap<String, String>> list = new ArrayList<>();

    public OrderListAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.customer_order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        HashMap<String, String> map = list.get(position);

        holder.ordercode.setText(map.get("orderCode"));
        holder.deliverydate.setText(map.get("date"));
        holder.status.setText(map.get("type"));

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderDetail.class);
                intent.putExtra("OrderId", map.get("orderCode"));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ordercode, deliverydate, status;
        CardView cardview;
        Button next;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ordercode = itemView.findViewById(R.id.ordercode);
            deliverydate = itemView.findViewById(R.id.deliverydate);
            status = itemView.findViewById(R.id.status);
            next = itemView.findViewById(R.id.next);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }
}
