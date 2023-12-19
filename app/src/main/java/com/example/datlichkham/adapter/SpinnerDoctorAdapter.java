package com.example.datlichkham.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.datlichkham.R;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;

import java.util.ArrayList;

public class SpinnerDoctorAdapter extends BaseAdapter {
    ArrayList<DoctorDTO> list;
    Context context;

    public SpinnerDoctorAdapter(ArrayList<DoctorDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        DoctorDTO obj = list.get(position);
        return obj;
    }

    @Override
    public long getItemId(int position) {
        DoctorDTO obj = list.get(position);
        return obj.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null) {
            row = View.inflate(context, R.layout.item_sp_doctor, null);
        } else {
            row = convertView;
        }
        DoctorDTO doctorDTO = list.get(position);
        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        TextView textView = row.findViewById(R.id.tv_nameDoctor);
        textView.setText(accountDTO.getFullName());
        return row;
    }
}
