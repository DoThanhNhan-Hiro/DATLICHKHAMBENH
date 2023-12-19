package com.example.datlichkham.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.DoctorAdapter;
import com.example.datlichkham.admin.AddDoctorActivity;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dto.DoctorDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment_ManagerDoctor extends Fragment {
    private FloatingActionButton fab_add_doctor;
    private RecyclerView rcv_list_doctor;
    private ArrayList<DoctorDTO> listDtoDoctor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__manager_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab_add_doctor = view.findViewById(R.id.fab_add_doctor);
        rcv_list_doctor = view.findViewById(R.id.rcv_list_doctor);


        DoctorDAO doctorDAO = new DoctorDAO(getContext());
        listDtoDoctor = doctorDAO.selectAll();
        DoctorAdapter adapter = new DoctorAdapter(listDtoDoctor,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcv_list_doctor.setLayoutManager(manager);
        rcv_list_doctor.setAdapter(adapter);

        fab_add_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddDoctorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DoctorDAO doctorDAO=new DoctorDAO(getContext());
        listDtoDoctor = doctorDAO.selectAll();
        DoctorAdapter adapter = new DoctorAdapter(listDtoDoctor,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcv_list_doctor.setLayoutManager(manager);
        rcv_list_doctor.setAdapter(adapter);
    }
}