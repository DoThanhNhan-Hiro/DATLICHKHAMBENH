package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class ItemHistoryOrderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNote;
    public TextView tvOrderId,tvNamefile,tvNameDoctor,tvNameService,tvNameRooms,tvStartDate,tvStartTime,tvOrderTime,tvOrderDate,tvPrice,tvStatusOrder;
    public ItemHistoryOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvOrderId = itemView.findViewById(R.id.tvOrderId);
        tvNamefile = itemView.findViewById(R.id.tvNamefile);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvNameService = itemView.findViewById(R.id.tvNameService);
        tvNameRooms = itemView.findViewById(R.id.tvNameRooms);
        tvStartDate = itemView.findViewById(R.id.tvStartDate);
        tvStartTime = itemView.findViewById(R.id.tvStartTime);
        tvOrderTime = itemView.findViewById(R.id.tvOrderTime);
        tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        tvStatusOrder = itemView.findViewById(R.id.tvStatusOrder);


        tvNote = itemView.findViewById(R.id.tvNote);

    }
}
