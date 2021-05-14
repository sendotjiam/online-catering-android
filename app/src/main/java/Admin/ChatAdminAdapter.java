package Admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sendo.onlinecatering.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ChatAdminAdapter extends RecyclerView.Adapter<ChatAdminAdapter.ViewHolder> {


    Context context;
    ArrayList<ChatAdminList> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.chat_admin_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.message_content.setText(list.get(position).getMessage_content());
        holder.time.setText(list.get(position).getTime());
        holder.customer_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatwithCustomer.class);
//                OrderList orderlist = new OrderList(list.get(position).getOrder_Code(), list.get(position).getDate(),
//                        list.get(position).getStatus());
//                intent.putExtra("orderList", orderlist);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setArrayListdata(ArrayList<ChatAdminList> list) {
        this.list = list;
    }

    public void filterlist(ArrayList<ChatAdminList> filteredlist){
        list = filteredlist;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_profile;
        TextView name, message_content, time;
        LinearLayout customer_click;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_profile = itemView.findViewById(R.id.img_profile);
            name = itemView.findViewById(R.id.name);
            message_content = itemView.findViewById(R.id.message_content);
            time = itemView.findViewById(R.id.time);
            customer_click = itemView.findViewById(R.id.customer_click);

        }
    }
}
