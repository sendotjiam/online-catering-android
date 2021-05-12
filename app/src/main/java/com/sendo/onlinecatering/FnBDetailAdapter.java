package com.sendo.onlinecatering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FnBDetailAdapter extends RecyclerView.Adapter<FnBDetailAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FnBDetail> fnBDetails;

    public FnBDetailAdapter(Context context) {
        this.context = context;
    }

    public FnBDetailAdapter(ArrayList<FnBDetail> fnBDetails) {
        this.fnBDetails = fnBDetails;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<FnBDetail> getFnBDetails() {
        return fnBDetails;
    }

    public void setFnBDetails(ArrayList<FnBDetail> fnBDetails) {
        this.fnBDetails = fnBDetails;
    }

    @NonNull
    @Override
    public FnBDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fnbdetail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FnBDetailAdapter.ViewHolder holder, int position) {
        holder.fnbname.setText(fnBDetails.get(position).getFnbname());
        holder.fnbprice.setText(fnBDetails.get(position).getFnbprice());
    }

    @Override
    public int getItemCount() {
        return fnBDetails.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView fnbname;
        TextView fnbprice;
        public ViewHolder(View itemView) {
            super(itemView);
            fnbname = itemView.findViewById(R.id.fnb_name);
            fnbprice = itemView.findViewById(R.id.fnb_price);
        }
    }
}
