package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class ItemListServiceViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgService;
    public TextView tvNameService;
    public ItemListServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        imgService = itemView.findViewById(R.id.imgService);
        tvNameService = itemView.findViewById(R.id.tvNameService);
    }
}
