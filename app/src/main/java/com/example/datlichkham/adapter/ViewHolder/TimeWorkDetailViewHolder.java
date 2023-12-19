package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class TimeWorkDetailViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTimeWorkDetail,tvUpdateTimeWorkDetail,tvTimeWork;
    public TimeWorkDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTimeWorkDetail = itemView.findViewById(R.id.tvTimeWorkDetail);
        tvUpdateTimeWorkDetail = itemView.findViewById(R.id.tvUpdateTimeWorkDetail);
        tvTimeWork = itemView.findViewById(R.id.tvTimeWork);
    }
}
