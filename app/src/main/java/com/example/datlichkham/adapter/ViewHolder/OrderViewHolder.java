package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameDoctor,tvNameService,tvNameRooms,tvStartDate,tvStartTime,tvPrice,tvNamefile;
    public ImageView imgDelete;
    public Button btn_cancel;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvNameService = itemView.findViewById(R.id.tvNameService);
        tvNameRooms = itemView.findViewById(R.id.tvNameRooms);
        tvStartDate = itemView.findViewById(R.id.tvStartDate);
        tvStartTime = itemView.findViewById(R.id.tvStartTime);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        imgDelete = itemView.findViewById(R.id.imgDelete);
        tvNamefile = itemView.findViewById(R.id.tvNamefile);
        btn_cancel =itemView.findViewById(R.id.cancer_order);
    }
}
