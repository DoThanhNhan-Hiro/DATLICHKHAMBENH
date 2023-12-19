package com.example.datlichkham.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.datlichkham.R;
import com.example.datlichkham.dto.TimeWorkDTO;

import java.util.ArrayList;

public class SpinnerTimeWorkAdapter extends BaseAdapter {
    private ArrayList<TimeWorkDTO> listTimeWorkDto = new ArrayList<>();
    private Context context;

    public SpinnerTimeWorkAdapter(ArrayList<TimeWorkDTO> listTimeWorkDto, Context context) {
        this.listTimeWorkDto = listTimeWorkDto;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listTimeWorkDto.size();
    }

    @Override
    public Object getItem(int i) {
        return listTimeWorkDto.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row = View.inflate(context, R.layout.item_sp_timework,null);
        }
        else{
            row = view;
        }

        TimeWorkDTO timeWorkDTO = listTimeWorkDto.get(i);
        TextView tvSession = row.findViewById(R.id.tvSession);
        tvSession.setText(timeWorkDTO.getSession());

        return row;
    }
}
