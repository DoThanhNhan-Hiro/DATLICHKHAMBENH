package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class DoctorOrderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameDoctor,tvTimeWork,tvDes,tvNameSerivce,tvPrice,tvNameRoom,tvOrderDate,tvClickOrder,tvBirthdayOrder;
    public RecyclerView rcv_list_timework_detail;
    public ImageView imgIconOrderDate;
    public DoctorOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameDoctor = itemView.findViewById(R.id.tvNameDoctor);
        tvTimeWork = itemView.findViewById(R.id.tvTimeWork);
        tvDes = itemView.findViewById(R.id.tvDes);
        tvNameSerivce = itemView.findViewById(R.id.tvNameSerivce);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        rcv_list_timework_detail = itemView.findViewById(R.id.rcv_list_timework_detail);
        imgIconOrderDate = itemView.findViewById(R.id.imgIconOrderDate);
        tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
        tvClickOrder = itemView.findViewById(R.id.tvClickOrder);
        tvBirthdayOrder = itemView.findViewById(R.id.tvBirthdayOrder);
    }
}
