package com.example.datlichkham.fragment.fragment_nav_user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.AdapterHistoryOrder;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dto.OrderDetailDTO;

import java.util.ArrayList;


public class Fragment_bookingHistory extends Fragment {
    private RecyclerView rcv_bookingHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_history, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_bookingHistory = view.findViewById(R.id.rcv_bookingHistory);

        OrderDetailDAO dao = new OrderDetailDAO(getContext());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("getIdUser", getContext().MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("idUser", -1);
        ArrayList<OrderDetailDTO> listOrderDetail = dao.getListOrderDetailDtoByNoConfirm(idUser);

        AdapterHistoryOrder adapter = new AdapterHistoryOrder(listOrderDetail, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_bookingHistory.setLayoutManager(manager);
        rcv_bookingHistory.setAdapter(adapter);
    }
}