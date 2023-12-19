package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class UpdateOrderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvOrderId,tvStatusOrder,tvNamefile,tvNameDoctor,tvNameService,tvNameRoomsUpateOrder,tvStartDateUpdateOrder,tvStartTimeUpateOrder,tvOrderTime,tvOrderDate,tvPrice;
    public Button btnUpdateOrder;
    public UpdateOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        btnUpdateOrder = itemView.findViewById(R.id.btnUpdateOrder);
        tvOrderId = itemView.findViewById(R.id.tvOrderId);
        tvStatusOrder = itemView.findViewById(R.id.tvStatusOrder);
        tvNamefile = itemView.findViewById(R.id.tvNamefile);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvNameService = itemView.findViewById(R.id.tvNameService);
        tvNameRoomsUpateOrder = itemView.findViewById(R.id.tvNameRoomsUpateOrder);
        tvStartDateUpdateOrder = itemView.findViewById(R.id.tvStartDateUpdateOrder);
        tvStartTimeUpateOrder = itemView.findViewById(R.id.tvStartTimeUpateOrder);
        tvOrderTime = itemView.findViewById(R.id.tvOrderTime);
        tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
        tvPrice = itemView.findViewById(R.id.tvPrice);

    }

}
