package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

public class RoomViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameRoom,tvLocationRoom;
    public ImageView imgUpdateRoom;
    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        tvLocationRoom = itemView.findViewById(R.id.tvLocationRoom);
        imgUpdateRoom = itemView.findViewById(R.id.imgUpdateRoom);
    }
}
