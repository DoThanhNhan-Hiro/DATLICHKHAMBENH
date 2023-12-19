package com.example.datlichkham.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.datlichkham.R;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;

public class SpinnerServiceAdapter extends BaseAdapter {
    private ArrayList<ServicesDTO> listService = new ArrayList<>();
    private Context context;

    public SpinnerServiceAdapter(ArrayList<ServicesDTO> listService, Context context) {
        this.listService = listService;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listService.size();
    }

    @Override
    public Object getItem(int i) {
        return listService.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = View.inflate(context, R.layout.item_sp_service,null);
        }
        else{
            row = view;
        }
        ServicesDTO servicesDTO = listService.get(i);
        TextView tvNameService = row.findViewById(R.id.tvNameService);
        tvNameService.setText(servicesDTO.getServicesName());
        return row;
    }
}
