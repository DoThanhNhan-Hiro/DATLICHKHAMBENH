package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.datlichkham.adapter.AdapterOrder;
import com.example.datlichkham.dao.OrderDAO;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dto.OrderDTO;
import com.example.datlichkham.dto.OrderDetailDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;

import java.util.ArrayList;
import java.util.Calendar;

public class ConfirmActivity extends AppCompatActivity {
    private RecyclerView rcv_list_order;
    private OrderDoctorDAO orderDoctorDAO;
    public TextView tvSumPrice;
    private float sumPrice = 0;
    private Button btnOrder, btnAddOrder;
    private ArrayList<OrderDoctorDTO> list = OrderDoctorActivity.listOrderDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        ImageView img_open_back = toolbar1.findViewById(R.id.img_open_back);
        img_open_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rcv_list_order = findViewById(R.id.rcv_list_order);
        tvSumPrice = findViewById(R.id.tvSumPrice);
        btnOrder = findViewById(R.id.btnOrder);
        btnAddOrder = findViewById(R.id.btnAddOrder);

        orderDoctorDAO = new OrderDoctorDAO(this);

        AdapterOrder adapterOrder = new AdapterOrder(list, this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_list_order.setLayoutManager(manager);
        rcv_list_order.setAdapter(adapterOrder);

        for (int i = 0; i < list.size(); i++) {
            sumPrice += list.get(i).getTotal();
        }
        tvSumPrice.setText("Thành tiền : " + sumPrice + "đ");

        OrderDAO orderDAO = new OrderDAO(this);
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(this);
        String date;
        //Lấy ra ngày hiện tại
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day >=10) {
            date = year + "/" + (month + 1) + "/" + day;
        }
        else{
            date = year + "/" + (month + 1) + "/0" + day;
        }


        //Lấy ra giờ hiện tại
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        String time = hour + ":" + minute;

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    OrderDoctorDTO orderDoctorDTO = list.get(i);
                    OrderDTO orderDTO = new OrderDTO();
                    orderDTO.setFile_id(orderDoctorDTO.getFile_id());
                    orderDTO.setOrder_date(date);
                    orderDTO.setOrder_time(time);
                    orderDTO.setStatus("Chờ bác sĩ xác nhận! ");
                    long res = orderDAO.insertRow(orderDTO);
                }
                ArrayList<OrderDTO> listOrderDto = orderDAO.selectDesc();
                for (int i = 0; i < list.size(); i++) {
                    OrderDTO orderDTO = listOrderDto.get(i);
                    OrderDoctorDTO orderDoctorDTO = OrderDoctorActivity.listOrderDoctor.get(i);

                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                    orderDetailDTO.setOrder_id(orderDTO.getId());
                    orderDetailDTO.setOrderDoctor_id(orderDoctorDTO.getId());
                    Log.e("TAG", "onClick: item:"+i+" order id: "+orderDTO.getId() );
                    Log.e("TAG", "onClick: item:"+i+" orderDoctor id: "+orderDoctorDTO.getId() );
                    long res = orderDetailDAO.innsertRow(orderDetailDTO);
                }
                OrderDoctorActivity.listOrderDoctor.clear();
                Dialog dialog = new Dialog(ConfirmActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialo_booked);
                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                } else {
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
                img_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("back", 1);
                        startActivity(intent);
                    }
                });
                dialog.show();

            }
        });

        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        orderDoctorDAO = new OrderDoctorDAO(this);
        AdapterOrder adapterOrder = new AdapterOrder(list, this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_list_order.setLayoutManager(manager);
        rcv_list_order.setAdapter(adapterOrder);
        for (int i = 0; i < list.size(); i++) {
            sumPrice += list.get(i).getTotal();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OrderDoctorActivity.listOrderDoctor.clear();
    }
}