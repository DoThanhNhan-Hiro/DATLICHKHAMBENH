package com.example.datlichkham.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.datlichkham.R;
import com.example.datlichkham.dto.RoomsDTO;

import java.util.ArrayList;

public class SpinneRoomsAdapter extends BaseAdapter {
    private ArrayList<RoomsDTO> listDtoRooms = new ArrayList<>();
    private Context context;

    public SpinneRoomsAdapter(ArrayList<RoomsDTO> listDtoRooms, Context context) {
        this.listDtoRooms = listDtoRooms;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDtoRooms.size();
    }

    @Override
    public Object getItem(int i) {
        return listDtoRooms.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = View.inflate(context, R.layout.item_sp_rooms,null);
        }
        else{
            row = view;
        }
        RoomsDTO roomsDTO = listDtoRooms.get(i);
        TextView tvNameRoom = row.findViewById(R.id.tvNameRoom);
        tvNameRoom.setText(roomsDTO.getName());
        return row;
    }
}
