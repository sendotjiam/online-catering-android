package com.sendo.onlinecatering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.MenuViewHolder> {

    private Context context;
    private ArrayList<Menus> menuArrayList = new ArrayList<>();

    public AllMenuAdapter(Context context, ArrayList<Menus> menuArrayList) {
        this.context = context;
        this.menuArrayList = menuArrayList;
    }

    public void setMenuArrayList(ArrayList<Menus> menuArrayList) {
        this.menuArrayList.clear();
        this.menuArrayList = menuArrayList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menudetail, parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        Menus menu = menuArrayList.get(position);

        /*byte[] foodimage = menu.getMenu_img_path();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage, 0, foodimage.length);
        holder.ivMenuImage.setImageBitmap(bitmap);
        holder.ivMenuImage.setScaleType(ImageView.ScaleType.CENTER_CROP);*/

        Picasso.get().load(menu.getMenu_img_path()).into(holder.ivMenuImage);
        holder.ivMenuImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        holder.tvMenuName.setText(menu.getMenu_name());
        holder.tvMenuPrice.setText(menu.getMenu_price() + "");

        holder.llMenuDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, MenuDetailsPage.class);
                detailIntent.putExtra("menuId", menu.getMenu_id());
                detailIntent.putExtra("menu", menu);
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
