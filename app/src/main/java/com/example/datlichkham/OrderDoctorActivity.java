package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;
import com.example.datlichkham.dto.RoomsDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.example.datlichkham.fragment.Fragment_order_you;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderDoctorActivity extends AppCompatActivity {
    private TextView tvNameDoctor, tvNameService, tvNameRooms, tvStartDate, tvStartTime, tvPrice;
    private RadioButton rdoOrderYou, rdoOrderI;
    private FrameLayout frameLayout;
    public static ArrayList<OrderDoctorDTO> listOrderDoctor = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_doctor);
        init();
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        ImageView img_open_back = toolbar1.findViewById(R.id.img_open_back);
        img_open_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        int idDoctor = intent.getIntExtra("idDoctor", -1);
        String startTime = intent.getStringExtra("startTime");
        String startDate = intent.getStringExtra("startDate");
        tvStartDate.setText(formatDate(startDate));
        tvStartTime.setText(startTime);

        SharedPreferences sharedPreferences = getSharedPreferences("getOrderDoctor",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("startDate",startDate);
        editor.putString("startTime",startTime);
        editor.putInt("idDoctor",idDoctor);
        editor.commit();

        DoctorDAO doctor = new DoctorDAO(this);
        DoctorDTO doctorDTO = doctor.getDtoDoctorByIdDoctor(idDoctor);

        AccountDAO account = new AccountDAO(this);
        AccountDTO accountDTO = account.getDtoAccount(doctorDTO.getUser_id());
        tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO service = new ServicesDAO(this);
        ServicesDTO servicesDTO = service.getDtoServiceByIdByService(doctorDTO.getService_id());
        tvNameService.setText(servicesDTO.getServicesName());

        RoomsDAO room = new RoomsDAO(this);
        RoomsDTO roomsDTO = room.getDtoRoomByIdRoom(doctorDTO.getRoom_id());

        tvNameRooms.setText(roomsDTO.getName());
        tvStartTime.setText(startTime);
        tvStartDate.setText(formatDate(startDate));
        tvPrice.setText(servicesDTO.getServicesPrice() + "đ");

        rdoOrderYou.setChecked(true);
        getFragment(new Fragment_order_you());

        rdoOrderYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment(new Fragment_order_you());
            }
        });
    }
    public String formatDate(String a) {
        String newDate ="";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 =a;
        SimpleDateFormat Format2 = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("dd/mm/yyyy", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public void init() {
        tvNameDoctor = findViewById(R.id.tvNameDoctor);
        tvNameService = findViewById(R.id.tvNameService);
        tvNameRooms = findViewById(R.id.tvNameRooms);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvPrice = findViewById(R.id.tvPrice);
        rdoOrderYou = findViewById(R.id.rdoOrderYou);
        frameLayout = findViewById(R.id.frameLayout);
    }
    public void getFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}