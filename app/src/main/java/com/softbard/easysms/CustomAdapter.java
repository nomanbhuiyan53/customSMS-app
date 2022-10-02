package com.softbard.easysms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    LayoutInflater inflater;
    List<String> id, name,mobile, product,os,mi,ov;

    public CustomAdapter(Context context,List<String> id, List<String> name, List<String> mobile, List<String> product, List<String> os, List<String> mi, List<String> ov) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.product = product;
        this.os = os;
        this.mi = mi;
        this.ov = ov;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycel_custom_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ID = id.get(position);
        String Name = name.get(position);
        String Mobile = mobile.get(position);
        String OS = os.get(position);
        String MI = mi.get(position);
        String PRd = product.get(position);
        String OV = ov.get(position);

        holder.cid.setText(ID);
        holder.name.setText(Name);
        holder.mobile.setText(Mobile);
        holder.totalOS.setText(OS);
        holder.product.setText(PRd);
        holder.over.setText(OV);
        holder.mini.setText(MI);
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,mobile,totalOS,mini,over,cid,product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mobile = itemView.findViewById(R.id.mobile);
            totalOS = itemView.findViewById(R.id.totalOS);
            mini = itemView.findViewById(R.id.mini);
            over = itemView.findViewById(R.id.over);
            cid = itemView.findViewById(R.id.cl_id);
            product = itemView.findViewById(R.id.product);

        }
    }
}
