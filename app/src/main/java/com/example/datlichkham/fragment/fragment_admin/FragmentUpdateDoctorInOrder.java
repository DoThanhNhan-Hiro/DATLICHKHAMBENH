package com.example.datlichkham.fragment.fragment_admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterUpdateDoctorInOrder;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dto.AllDTO;

import java.util.ArrayList;


public class FragmentUpdateDoctorInOrder extends Fragment {
    private RecyclerView rcvListOrderUpdateByfile;
    OrderDetailDAO orderDetailDAO;
    ArrayList<AllDTO> listOrder;
    AdapterUpdateDoctorInOrder adapterUpdateDoctorInOrder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_doctor_in_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListOrderUpdateByfile = view.findViewById(R.id.rcv_listOrderUpdateByfile);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("idFile", Context.MODE_PRIVATE);
        int idFile = sharedPreferences.getInt("idFile",-1);
        orderDetailDAO = new OrderDetailDAO(getContext());
        listOrder = orderDetailDAO.getListOrderByIdFile(idFile);
        adapterUpdateDoctorInOrder = new AdapterUpdateDoctorInOrder(listOrder,"nocomfrim", idFile);
        rcvListOrderUpdateByfile.setAdapter(adapterUpdateDoctorInOrder);
        if(listOrder.size()==0){
            Toast.makeText(getContext(), "Không có dữ liệu" , Toast.LENGTH_SHORT).show();
        }


    }
}