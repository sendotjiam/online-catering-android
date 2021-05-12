package com.sendo.onlinecatering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CheckOutDetailAdapter extends RecyclerView.Adapter<CheckOutDetailAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CheckOutDetail> checkOutDetails;

    public CheckOutDetailAdapter(Context context) {
        this.context = context;
    }

    public CheckOutDetailAdapter(ArrayList<CheckOutDetail> checkOutDetails) {
        this.checkOutDetails = checkOutDetails;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<CheckOutDetail> getCheckOutDetails() {
        return checkOutDetails;
    }

    public void setCheckOutDetails(ArrayList<CheckOutDetail> checkOutDetails) {
        this.checkOutDetails = checkOutDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkoutdetail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutDetailAdapter.ViewHolder holder, int position) {
        holder.fnbimage.setImageResource(checkOutDetails.get(position).getImage_src());
        holder.fnbname.setText(checkOutDetails.get(position).getFnbname());
        holder.fnbdetail.setText(checkOutDetails.get(position).getFnbdetail());
        holder.note.setText(checkOutDetails.get(position).getNotes());
        holder.date.setText(checkOutDetails.get(position).getDate());
        holder.price.setText(checkOutDetails.get(position).getFnbprice());
    }

    @Override
    public int getItemCount() {
        return checkOutDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fnbimage;
        TextView fnbname;
        TextView fnbdetail;
        TextView note;
        TextView date;
        TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            fnbimage = itemView.findViewById(R.id.fnbimage);
            fnbname = itemView.findViewById(R.id.name);
            fnbdetail = itemView.findViewById(R.id.detail);
            note = itemView.findViewById(R.id.notesdetail);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.price);
        }
    }
}
