package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datlichkham.adapter.SpinnerDoctorAdapter;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dao.OrderDAO;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dao.TimeWorkDetailDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.FileDTO;
import com.example.datlichkham.dto.OrderDTO;
import com.example.datlichkham.dto.OrderDetailDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;
import com.example.datlichkham.dto.RoomsDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.example.datlichkham.dto.TimeWorkDTO;

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateOrderActivity extends AppCompatActivity {
    private TextView tvIdOrder, tvNamefile, tvNameService, tvNameRooms, tvStartDate, tvStartTime, tvNameDoctor, tvPhoneNumeber, tvCall, tvOrderTime, tvOrderDate, tvPrice;
    private Spinner spDoctor;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);
        init();
        Intent intent = getIntent();
        int idOrderDoctorDetail = intent.getIntExtra("idOrderDetailDTO", -1);
        int index = intent.getIntExtra("index", -1);
        String today;
        //lay ngay hien tai
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day > 10) {
            today = year + "/" + (month + 1) + "/" + day;
        } else {
            today = year + "/" + (month + 1) + "/0" + day;

        }


        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(this);
        OrderDetailDTO orderDetailDTO = orderDetailDAO.getOrderDetialDto(idOrderDoctorDetail);

        OrderDAO orderDAO = new OrderDAO(this);
        OrderDTO orderDTO = orderDAO.getOrderDTOById(orderDetailDTO.getOrder_id());

        tvOrderDate.setText("Ngày đặt lịch khám: " + orderDTO.getOrder_date());
        tvOrderTime.setText("Thời gian đặt lịch khám: " + orderDTO.getOrder_time());

        tvIdOrder.setText("Mã lịch khám: " + orderDetailDTO.getOrder_id());
        OrderDoctorDAO orderDoctorDAO = new OrderDoctorDAO(this);
        OrderDoctorDTO orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(orderDetailDTO.getOrderDoctor_id());

        FileDAO fileDAO = new FileDAO(this);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        tvNamefile.setText(fileDTO.getFullname());



        tvStartDate.setText(orderDoctorDTO.getStart_date());
        tvStartTime.setText(orderDoctorDTO.getStart_time());

        DoctorDAO doctorDAO = new DoctorDAO(this);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(orderDoctorDTO.getDoctor_id());

        AccountDAO accountDAO = new AccountDAO(this);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());


        ServicesDAO servicesDAO = new ServicesDAO(this);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        tvNameService.setText(servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(this);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        tvNameRooms.setText(roomsDTO.getName());

        tvPrice.setText(servicesDTO.getServicesPrice() + "vnđ");

        AccountDTO accountDTO1 = accountDAO.getDtoAccount(fileDTO.getUser_id());
        tvPhoneNumeber.setText(accountDTO1.getPhoneNumber());

        TimeWorkDetailDAO timeWorkDetailDAO = new TimeWorkDetailDAO(this);
        timeWorkDetailDAO.open();

        ArrayList<TimeWorkDTO> list = timeWorkDetailDAO.listTimeWork(orderDoctorDTO.getStart_time());
//        Toast.makeText(this, list.get(0).getId()+"", Toast.LENGTH_SHORT).show();
        ArrayList<DoctorDTO> ListDoctorByIdService = doctorDAO.getListDoctorByIdService(doctorDTO.getService_id(), list.get(0).getId(),doctorDTO.getService_id());
//        Toast.makeText(this, ListDoctorByIdService.size()+"", Toast.LENGTH_SHORT).show();
        String TAG = "zzzzzzzzzzzzzzzzzzzzzzz";
        for(int i=0;i<ListDoctorByIdService.size();i++){
            Log.d(TAG, "onCreate: List1: "+ListDoctorByIdService.get(i).getId());
        }
        Log.d(TAG, "onCreate: -----------------------------------");
        ArrayList<DoctorDTO> listDoctorbyIdTimeWorkDetail = doctorDAO.listDoctorbyIdTimeWorkDetail(orderDoctorDTO.getStart_time(), orderDoctorDTO.getStart_date(), doctorDTO.getService_id());
        Log.d(TAG, "onCreate: Start_time: "+orderDoctorDTO.getStart_time());
        Log.d(TAG, "onCreate: Start_date: "+orderDoctorDTO.getStart_date());
        for(int i=0;i<listDoctorbyIdTimeWorkDetail.size();i++){
            Log.d(TAG, "onCreate: List2: "+listDoctorbyIdTimeWorkDetail.get(i).getId());
        }
//        Toast.makeText(this, listDoctorbyIdTimeWorkDetail.size()+"", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < ListDoctorByIdService.size(); i++) {
            for (int j = 0; j < listDoctorbyIdTimeWorkDetail.size(); j++) {
                if (ListDoctorByIdService.get(i).getId() == listDoctorbyIdTimeWorkDetail.get(j).getId()) {
                    ListDoctorByIdService.remove(i);
                }
            }
        }

        Log.d(TAG, "onCreate: -------------------------------");
        for(int i=0;i<ListDoctorByIdService.size();i++){
            Log.d(TAG, "onCreate: List1: "+ListDoctorByIdService.get(i).getId());
        }
        ListDoctorByIdService.add(0,doctorDTO);
        SpinnerDoctorAdapter adapterListDoctor = new SpinnerDoctorAdapter(ListDoctorByIdService, UpdateOrderActivity.this);
        spDoctor.setAdapter(adapterListDoctor);
        spDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                DoctorDTO doctorDTO1 = (DoctorDTO) spDoctor.getSelectedItem();
                RoomsDTO roomsDTO1 = roomsDAO.getDtoRoomByIdRoom(doctorDTO1.getRoom_id());
                AccountDTO accountDTO2 = accountDAO.getDtoAccount(doctorDTO1.getUser_id());
                tvNameRooms.setText(roomsDTO1.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorDTO doctorDTO1 = (DoctorDTO) spDoctor.getSelectedItem();
                orderDoctorDTO.setDoctor_id(doctorDTO1.getId());

                long res = orderDoctorDAO.updateRow(orderDoctorDTO);
                if(res>0){
                    Toast.makeText(UpdateOrderActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    ListDoctorByIdService.add(0,doctorDTO);
                    onBackPressed();

                }
            }
        });

        String phone = accountDTO.getPhoneNumber();
        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+phone));
                startActivity(intent1);
            }
        });




}

    public void init() {
        tvIdOrder = findViewById(R.id.tvIdOrder);
        tvNamefile = findViewById(R.id.tvNamefile);
        tvNameService = findViewById(R.id.tvNameService);
        tvNameRooms = findViewById(R.id.tvNameRooms);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvPhoneNumeber = findViewById(R.id.tvPhoneNumeber);
        tvCall = findViewById(R.id.tvCall);
        tvOrderTime = findViewById(R.id.tvOrderTime);
        tvOrderDate = findViewById(R.id.tvOrderDate);
        tvPrice = findViewById(R.id.tvPrice);
        spDoctor = findViewById(R.id.spDoctor);
        btnSave = findViewById(R.id.btnSave);


    }
}