package com.sendo.onlinecatering;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    ArrayList<Menus> cartMenus;
    CartDB cartDB;

    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public CartAdapter(Context context, ArrayList<Menus> cartMenus, CartDB cartDB) {
        this.cartMenus = cartMenus;
        this.context = context;
        this.cartDB = cartDB;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartAdapter.ViewHolder holder, int position) {
        Menus menu = cartMenus.get(position);
        byte[] foodImage = menu.getMenu_img_path();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.menuImg.setImageBitmap(bitmap);
        holder.menuImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.menuName.setText(menu.getMenu_name());
        holder.itemName.setText(menu.getMenu_name());
        holder.menuPrice.setText(menu.getMenu_price() +  "");
        holder.menuDescription.setText(menu.getMenu_description());
    }

    @Override
    public int getItemCount() {
        return cartMenus.size();
    }

    public void remove(int id) {
        Log.v("id remove", id + "");
        cartMenus.remove(id);
        notifyItemRemoved(id);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuName, menuDescription, menuPrice, itemName;
        ImageView menuImg;
        Button btnDelete;

        public ViewHolder(@NonNull @NotNull View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            menuName = itemView.findViewById(R.id.name);
            itemName = itemView.findViewById(R.id.menu_name);
            menuDescription = itemView.findViewById(R.id.detail);
            menuPrice = itemView.findViewById(R.id.menu_price);
            menuImg = itemView.findViewById(R.id.fnbimage);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(view -> {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        clickListener.onDeleteClick(position);
                        Toast.makeText(context, getItemCount() + " items", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


}
