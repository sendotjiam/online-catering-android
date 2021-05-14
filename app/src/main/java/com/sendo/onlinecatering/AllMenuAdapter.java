package com.sendo.onlinecatering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.MenuViewHolder> {

    private Context context;
    private ArrayList<Menu> menuArrayList;

    public AllMenuAdapter(Context context, ArrayList<Menu> menuArrayList) {
        this.context = context;
        this.menuArrayList = menuArrayList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menudetail, parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        Menu menu = menuArrayList.get(position);

        holder.ivMenuImage.setImageResource(R.drawable.ayamgoreng);
        holder.tvMenuName.setText(menu.getName());
        holder.tvMenuPrice.setText(menu.getPrice() + "");

        holder.llMenuDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, MenuDetailsPage.class);
                ((Activity) context).startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llMenuDetail;
        ImageView ivMenuImage;
        TextView tvMenuName, tvMenuPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            llMenuDetail = itemView.findViewById(R.id.ll_menuDetail);
            ivMenuImage = itemView.findViewById(R.id.iv_menu_image);
            tvMenuName = itemView.findViewById(R.id.tv_menu_name);
            tvMenuPrice = itemView.findViewById(R.id.tv_menu_price);
        }
    }
}
