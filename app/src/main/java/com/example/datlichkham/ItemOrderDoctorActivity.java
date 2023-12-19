package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.datlichkham.adapter.AdapterListTimeWorkDetail;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dao.TimeWorkDAO;
import com.example.datlichkham.dao.TimeWorkDetailDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.RoomsDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.example.datlichkham.dto.TimeWorkDTO;
import com.example.datlichkham.dto.TimeWorkDetailDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ItemOrderDoctorActivity extends AppCompatActivity {
    private TextView tvNameDoctor, tvDes, tvTimeWork, tvNameSerivce, tvPrice, tvNameRoom,tvOrderDate,tvClickOrder;
    private ImageView imgIconOrderDate;
    private RecyclerView rcv_list_timework_detail;
    private TextView tvBirthdayOrder;
    private ImageView imgBirthdayOrder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_order_doctor);
        init();
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        ImageView img_open_back = toolbar1.findViewById(R.id.img_open_back);
        img_open_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgIconOrderDate.setVisibility(View.GONE);
        tvOrderDate.setVisibility(View.GONE);
        tvClickOrder.setVisibility(View.GONE);
        TimeWorkDetailDAO timeWorkDetailDAO = new TimeWorkDetailDAO(this);
        timeWorkDetailDAO.open();
        Intent intent = getIntent();
        int idDoctor = intent.getIntExtra("idDoctor", -1);

        SharedPreferences sharedPreferences = getSharedPreferences("getIdOrderDoctor", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idDoctor", idDoctor);
        DoctorDAO doctorDAO = new DoctorDAO(this);

        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(idDoctor);
        AccountDAO accountDAO = new AccountDAO(this);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        tvNameDoctor.setText(accountDTO.getFullName());

        tvDes.setText(doctorDTO.getDescription());

        TimeWorkDAO timeWorkDAO = new TimeWorkDAO(this);
        TimeWorkDTO timeWorkDTO = timeWorkDAO.getDtoTimeWork(doctorDTO.getTimework_id());
        tvTimeWork.setText(timeWorkDTO.getSession());
        tvTimeWork.setVisibility(View.GONE);
        tvBirthdayOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(ItemOrderDoctorActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = day + "/" + (month + 1) + "/" + year;
                        tvBirthdayOrder.setText(date);
                        editor.putString("startDate", formatDate(date));
                        editor.commit();
                        ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto = timeWorkDetailDAO.selectTimeWorkDetailByTimeWorkId(timeWorkDTO.getId());
                        AdapterListTimeWorkDetail adapterListTimeWorkDetail = new AdapterListTimeWorkDetail(listTimeWorkDetailDto, ItemOrderDoctorActivity.this);
                        LinearLayoutManager manager = new LinearLayoutManager(ItemOrderDoctorActivity.this, RecyclerView.HORIZONTAL, false);
                        rcv_list_timework_detail.setLayoutManager(manager);
                        rcv_list_timework_detail.setAdapter(adapterListTimeWorkDetail);
                        imgIconOrderDate.setVisibility(View.VISIBLE);
                        tvOrderDate.setVisibility(View.VISIBLE);
                        tvClickOrder.setVisibility(View.VISIBLE);
                        tvTimeWork.setVisibility(View.VISIBLE);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
        ServicesDAO servicesDAO = new ServicesDAO(this);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        tvNameSerivce.setText(servicesDTO.getServicesName());
        tvPrice.setText(servicesDTO.getServicesPrice() + "đ");


        RoomsDAO roomsDAO = new RoomsDAO(this);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        tvNameRoom.setText(roomsDTO.getName());
    }
    public String formatDate(String a) {
        String newDate ="";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 =a;
        SimpleDateFormat Format2 = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("yyyy/mm/dd", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }
    public void init() {
        tvNameDoctor = findViewById(R.id.tvNameDoctor);
        tvDes = findViewById(R.id.tvDes);
        tvTimeWork = findViewById(R.id.tvTimeWork);
        tvNameSerivce = findViewById(R.id.tvNameSerivce);
        tvPrice = findViewById(R.id.tvPrice);
        tvNameRoom = findViewById(R.id.tvNameRoom);
        rcv_list_timework_detail = findViewById(R.id.rcv_list_timework_detail);
        imgIconOrderDate = findViewById(R.id.imgIconOrderDate);
        tvOrderDate = findViewById(R.id.tvOrderDate);
        tvClickOrder = findViewById(R.id.tvClickOrder);
        tvBirthdayOrder = findViewById(R.id.tvBirthdayOrder);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}