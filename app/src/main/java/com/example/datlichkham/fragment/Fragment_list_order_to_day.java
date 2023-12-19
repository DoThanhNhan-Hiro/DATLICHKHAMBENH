package com.example.datlichkham.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterUpdateOrder;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dto.OrderDetailDTO;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_list_order_to_day extends Fragment {
    private RecyclerView rcv_list_order_today;
    private TextView tvSumNumberOrderToDay;
    private OrderDetailDAO orderDetailDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_order_to_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_order_today = view.findViewById(R.id.rcv_list_order_today);
        tvSumNumberOrderToDay = view.findViewById(R.id.tvSumNumberOrderToDay);

        //Lay ngay hien tai
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        String today;
        if (day >= 10) {
            today = year + "/" + (month + 1) + "/" + day;
        } else {
            today = year + "/" + (month + 1) + "/0" + day;
        }

        orderDetailDAO = new OrderDetailDAO(getContext());
        ArrayList<OrderDetailDTO> listOrderDetail = orderDetailDAO.getListOrderToDay(today);

        AdapterUpdateOrder adapterOrder = new AdapterUpdateOrder(listOrderDetail, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_list_order_today.setLayoutManager(manager);
        rcv_list_order_today.setAdapter(adapterOrder);

        tvSumNumberOrderToDay.setText("Tổng đơn đặt: " + listOrderDetail.size());
    }
    @Override
    public void onResume() {
        super.onResume();
        //Lay ngay hien tai
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String today;
        if (day >= 10) {
            today = year + "/" + (month + 1) + "/" + day;
        } else {
            today = year + "/" + (month + 1) + "/0" + day;
        }
        ArrayList<OrderDetailDTO> listOrderDetail = orderDetailDAO.getListOrderToDay(today);
        AdapterUpdateOrder adapterOrder = new AdapterUpdateOrder(listOrderDetail, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_list_order_today.setLayoutManager(manager);
        rcv_list_order_today.setAdapter(adapterOrder);
        tvSumNumberOrderToDay.setText("Tổng đơn đặt: " + listOrderDetail.size());

    }
}
