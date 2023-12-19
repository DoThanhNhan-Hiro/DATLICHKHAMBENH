package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.datlichkham.adapter.AdapterDoctorOrder;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dto.DoctorDTO;

import java.util.ArrayList;

public class ListDoctorActivity extends AppCompatActivity {
    private RecyclerView rcv_list_doctors;
    private DoctorDAO doctorDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctor);
        rcv_list_doctors = findViewById(R.id.rcv_list_doctors);
        doctorDAO = new DoctorDAO(this);

        ArrayList<DoctorDTO> ds = doctorDAO.selectAll();
        AdapterDoctorOrder adapter = new AdapterDoctorOrder(ds,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_list_doctors.setLayoutManager(manager);
        rcv_list_doctors.setAdapter(adapter);

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        ImageView img_open_back = toolbar1.findViewById(R.id.img_open_back);
        img_open_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}