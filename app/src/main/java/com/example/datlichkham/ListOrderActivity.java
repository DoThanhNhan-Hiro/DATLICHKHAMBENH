package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.datlichkham.adapter.AdapterHistoryOrder;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dto.OrderDetailDTO;

import java.util.ArrayList;

public class ListOrderActivity extends AppCompatActivity {
    private RecyclerView rcv_list_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        rcv_list_order = findViewById(R.id.rcv_list_order);

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(this);
        SharedPreferences sharedPreferences = getSharedPreferences("getIdUser",MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("idUser",-1);
        ArrayList<OrderDetailDTO> ds = orderDetailDAO.getListOrderDetailDtoByNoConfirm(idUser);

        AdapterHistoryOrder adapter = new AdapterHistoryOrder(ds,this);
        LinearLayoutManager manager  =new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_list_order.setLayoutManager(manager);
        rcv_list_order.setAdapter(adapter);

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