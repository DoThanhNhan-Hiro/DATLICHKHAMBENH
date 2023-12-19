package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.datlichkham.adapter.AdapterListService2;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private TextView tvSearch;
    private Button btnSearch;
    private RecyclerView rcv_list_services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tvSearch = findViewById(R.id.tvSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rcv_list_services = findViewById(R.id.rcv_list_services);

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        ImageView img_open_back = toolbar1.findViewById(R.id.img_open_back);
        img_open_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServicesDAO dao = new ServicesDAO(SearchActivity.this);
                ArrayList<ServicesDTO> list = dao.getDtoServiceByIdByNameService(tvSearch.getText().toString());
                if (list.size() <= 0) {
                    Toast.makeText(SearchActivity.this, "Không tìm thấy !", Toast.LENGTH_SHORT).show();
                } else {
                    AdapterListService2 ds = new AdapterListService2(list, SearchActivity.this);
                    LinearLayoutManager manager = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);
                    rcv_list_services.setLayoutManager(manager);
                    rcv_list_services.setAdapter(ds);
                }
            }
        });

    }
}