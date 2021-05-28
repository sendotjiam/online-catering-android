package Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sendo.onlinecatering.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class OrderDetailListAdapter extends RecyclerView.Adapter<OrderDetailListAdapter.ViewHolder> {

    NumberFormat formatter = new DecimalFormat("#,###");

    Context context;
    ArrayList<OrderList> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.order_detail_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String formatnominal = formatter.format(Integer.parseInt(list.get(position).getOrder_menu_price()));

        holder.foodname.setText(list.get(position).getOrder_menu_name());
        holder.foodprice.setText("Rp. " + formatnominal);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setArrayListdata(ArrayList<OrderList> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodname, foodprice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname = itemView.findViewById(R.id.foodname);
            foodprice = itemView.findViewById(R.id.foodprice);

        }
    }
}
