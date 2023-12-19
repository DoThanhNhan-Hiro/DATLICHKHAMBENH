package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class ItemListTimeWorkDetailViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameTimeWorkDetail;
    public ItemListTimeWorkDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameTimeWorkDetail = itemView.findViewById(R.id.tvNameTimeWorkDetail);
    }
}
