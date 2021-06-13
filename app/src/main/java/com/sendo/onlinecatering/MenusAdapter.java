package com.sendo.onlinecatering;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MenusAdapter extends RecyclerView.Adapter<MenusAdapter.ViewHolder> {

    Context context;
    ArrayList<Menus> menuList;
    int userId = 0;

    public MenusAdapter(Context context, ArrayList<Menus> menuList, int userId) {
        this.context = context;
        this.menuList = menuList;
        this.userId = userId;
    }

    public void setMenuArrayList(ArrayList<Menus> menuArrayList) {
        this.menuList.clear();
        this.menuList = menuArrayList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MenusAdapter.ViewHolder holder, int position) {
        Menus menu = menuList.get(position);
        byte[] foodImage = menu.getMenu_img_path();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.menuImg.setImageBitmap(bitmap);
        holder.menuImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.menuName.setText(menu.getMenu_name());
        holder.menuDescription.setText(menu.getMenu_description());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView menuImg;
        TextView menuName, menuDescription;
        CartDB cartDB = new CartDB(context);
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            menuImg = itemView.findViewById(R.id.fnbimage);
            menuName = itemView.findViewById(R.id.name);
            menuDescription = itemView.findViewById(R.id.detail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Menus menu = menuList.get(position);
            showDetail(menu);
        }

        public void showDetail(Menus menu) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle(menu.getMenu_name());
            alertDialogBuilder
                    .setMessage(menu.getMenu_description())
                    .setCancelable(false)
                    .setPositiveButton("Add to Cart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cartDB.addToCart(userId, menu.getMenu_id());
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            alertDialogBuilder.show();
        }
    }

}
