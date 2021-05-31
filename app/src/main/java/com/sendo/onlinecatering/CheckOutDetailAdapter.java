package com.sendo.onlinecatering;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CheckOutDetailAdapter extends RecyclerView.Adapter<CheckOutDetailAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Menus> menus;
    NumberFormat formatter = new DecimalFormat("#,###");

    public CheckOutDetailAdapter(Context context) {
        this.context = context;
    }

    public CheckOutDetailAdapter(ArrayList<Menus> menus) {
        this.menus = menus;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Menus> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menus> menus) {
        this.menus = menus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkoutdetail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutDetailAdapter.ViewHolder holder, int position) {
        File imgfile = new File(String.valueOf(menus.get(position).getMenu_img_path()));
        if(imgfile.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());
            holder.fnbimage.setImageBitmap(bitmap);
        }
//        String path = Environment.getExternalStorageState() + "/" +menus.get(position).getMenu_img_path() + "/";
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
//        holder.fnbimage.setImageBitmap(bitmap);
//        Glide.with(context).load(new File(menus.get(position).getMenu_img_path())).into(holder.fnbimage);
        holder.fnbname.setText(menus.get(position).getMenu_name());
        holder.fnbdetail.setText(menus.get(position).getMenu_description());
        holder.fnbname2.setText(menus.get(position).getMenu_name());
        String formattampung = formatter.format(menus.get(position).getMenu_price());
        holder.price.setText("Rp." + formattampung + ",00");
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fnbimage;
        TextView fnbname;
        TextView fnbdetail;
        TextView fnbname2;
        TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            fnbimage = itemView.findViewById(R.id.fnbimage);
            fnbname = itemView.findViewById(R.id.name);
            fnbdetail = itemView.findViewById(R.id.detail);
            fnbname2 = itemView.findViewById(R.id.name2);
            price = itemView.findViewById(R.id.price);
        }
    }
}
