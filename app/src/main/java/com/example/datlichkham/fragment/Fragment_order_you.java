package com.example.datlichkham.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.datlichkham.ConfirmActivity;
import com.example.datlichkham.OrderDoctorActivity;
import com.example.datlichkham.R;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.FileDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Fragment_order_you extends Fragment {

    private TextInputLayout tilNameFullName;
    private TextInputLayout tilPhoneNumber;
    private TextView tvBirthday;
    private ImageView imgBirthday;
    private TextInputLayout tilEmail;
    private TextInputLayout tilCccd;
    private TextInputLayout tilCountry;
    private RadioButton rdoYes;
    private RadioButton rdoNo;
    private TextInputLayout tilJob;
    private TextInputLayout tilAddress;
    private TextInputLayout tilDes;
    private MaterialButton btnOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_you,container,false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewID(view);
        SharedPreferences preferences = getActivity().getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
        int idUser = preferences.getInt("idUser",-1);
        AccountDAO accountDAO = new AccountDAO(getContext());
        AccountDTO accountDTO = accountDAO.getDtoAccount(idUser);
        tilNameFullName.getEditText().setText(accountDTO.getFullName());
        rdoYes.setChecked(true);

        FileDAO fileDAO = new FileDAO(getContext());

        SharedPreferences preferences1 = getActivity().getSharedPreferences("getOrderDoctor",Context.MODE_PRIVATE);
        String startTime = preferences1.getString("startTime","");
        String startDate = preferences1.getString("startDate","");
        int idDoctor = preferences1.getInt("idDoctor",-1);

        DoctorDAO doctorDAO = new DoctorDAO(getContext());
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(idDoctor);

        ServicesDAO servicesDAO = new ServicesDAO(getContext());
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());

        OrderDoctorDAO orderDoctorDao= new OrderDoctorDAO(getContext());
        ArrayList<FileDTO> listFileDto = fileDAO.getFileByIdUser(idUser);
        FileDTO fileDTO = listFileDto.get(0);
        tilAddress.getEditText().setText(fileDTO.getAddress());
        tilCccd.getEditText().setText(fileDTO.getCccd());
        tilCountry.getEditText().setText(fileDTO.getCountry());
        tilEmail.getEditText().setText(fileDTO.getEmail());
        tilJob.getEditText().setText(fileDTO.getJob());
        tilNameFullName.getEditText().setText(fileDTO.getFullname());
        tilPhoneNumber.getEditText().setText(fileDTO.getPhoneNumber());
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date d = f.parse(fileDTO.getBirthday());
            SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
            tvBirthday.setText(f1.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(fileDTO.getBhyt().equals("Không")){
            rdoNo.setChecked(true);
        }
        else{
            rdoYes.setChecked(true);
        }

        ArrayList<FileDTO> listFileDTO = fileDAO.getFileByIdUser(idUser);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
                    orderDoctorDTO.setFile_id(listFileDTO.get(0).getId());
                    orderDoctorDTO.setDoctor_id(idDoctor);
                    orderDoctorDTO.setStart_date(startDate);
                    orderDoctorDTO.setStart_time(startTime);
                    orderDoctorDTO.setTotal(servicesDTO.getServicesPrice());

                    long res1 = orderDoctorDao.insertRow(orderDoctorDTO);
                    OrderDoctorDTO orderDoctorDTO1 = orderDoctorDao.getOrderDoctorDtoDesc();
                    if(res1>0){
                        OrderDoctorActivity.listOrderDoctor.add(orderDoctorDTO1);
                        Intent intent = new Intent(getContext(), ConfirmActivity.class);
                        startActivity(intent);
                    }
            }
        });
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
    public void findViewID(View view){
        tilNameFullName = view.findViewById(R.id.tilNameFullName);
        tilPhoneNumber = view.findViewById(R.id.tilPhoneNumber);
        tvBirthday = view.findViewById(R.id.tvBirthday);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilCccd = view.findViewById(R.id.tilCccd);
        tilCountry = view.findViewById(R.id.tilCountry);
        rdoYes = view.findViewById(R.id.rdoYes);
        rdoNo = view.findViewById(R.id.rdoNo);
        tilJob = view.findViewById(R.id.tilJob);
        tilAddress = view.findViewById(R.id.tilAddress);
        tilDes = view.findViewById(R.id.tilDes);
        btnOrder = view.findViewById(R.id.btnOrder);
    }
}
