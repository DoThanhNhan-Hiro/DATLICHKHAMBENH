package com.example.datlichkham.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.ListDoctorByServiceActivity;
import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.ItemListServiceViewHolder;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterListService extends RecyclerView.Adapter<ItemListServiceViewHolder> {
    private ArrayList<ServicesDTO> listDtoService = new ArrayList<>();
    private Context context;

    public AdapterListService(ArrayList<ServicesDTO> listDtoService, Context context) {
        this.listDtoService = listDtoService;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_service,parent,false);
        return new ItemListServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListServiceViewHolder holder, int position) {
        ServicesDTO servicesDTO = listDtoService.get(position);
        holder.tvNameService.setText(servicesDTO.getServicesName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListDoctorByServiceActivity.class);
                intent.putExtra("idService",servicesDTO.getServicesId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDtoService.size();
    }
}
