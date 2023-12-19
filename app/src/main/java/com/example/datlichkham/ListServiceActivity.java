package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.datlichkham.adapter.AdapterListService2;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;

public class ListServiceActivity extends AppCompatActivity {
    private RecyclerView rcv_list_services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service);
        rcv_list_services = findViewById(R.id.rcv_list_services);

        ServicesDAO DAO = new ServicesDAO(this);

        ArrayList<ServicesDTO> ds=  DAO.selectAll();
        AdapterListService2 ds2 = new AdapterListService2(ds,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_list_services.setLayoutManager(manager);
        rcv_list_services.setAdapter(ds2);

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