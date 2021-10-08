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

import com.google.firebase.firestore.CollectionReference;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    ArrayList<Menus> cartMenus;
    CollectionReference cartReference;

    public CartAdapter(Context context, ArrayList<Menus> cartMenus, CollectionReference cartReference) {
        this.cartMenus = cartMenus;
        this.context = context;
        this.cartReference = cartReference;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartAdapter.ViewHolder holder, int position) {
        Menus menu = cartMenus.get(position);

        Picasso.get().load(menu.getMenu_img_path()).into(holder.menuImg);

        holder.menuImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.menuName.setText(menu.getMenu_name());
        holder.itemName.setText(menu.getMenu_name());
        holder.menuPrice.setText(menu.getMenu_price() +  "");
        holder.menuDescription.setText(menu.getMenu_description());

        holder.btnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cartReference.document(menu.getMenu_id()).delete();
                cartMenus.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartMenus.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuName, menuDescription, menuPrice, itemName;
        ImageView menuImg;
        Button btnDelete;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.name);
            itemName = itemView.findViewById(R.id.menu_name);
            menuDescription = itemView.findViewById(R.id.detail);
            menuPrice = itemView.findViewById(R.id.menu_price);
            menuImg = itemView.findViewById(R.id.fnbimage);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }

    }


}
