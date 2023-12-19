package com.example.datlichkham.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datlichkham.R;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.FileDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentItemFileDetail extends Fragment {
    private CircleImageView imgAvata;
    private TextInputLayout tilNameFullName;
    private TextInputLayout tilPhoneNumber;
    private TextInputLayout tilBirthday;
    private TextInputLayout tilEmail;
    private TextInputLayout tilCccd;
    private TextInputLayout tilCountry;
    private TextInputLayout tilbhyt;
    private TextInputLayout tilJob;
    private TextInputLayout tilAddress;
    private TextInputLayout tilDes;
    FileDAO fileDAO;
    FileDTO fileDTO;
    AccountDTO accountDTO;
    AccountDAO accountDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_file_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("idFile", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("idFile",-1);
        int idUser= sharedPreferences.getInt("id_user",-1);
        fileDAO = new FileDAO(getContext());
        fileDTO = fileDAO.getFileDToById(id);
        tilNameFullName.getEditText().setText(fileDTO.getFullname());
        tilAddress.getEditText().setText(fileDTO.getAddress());
        tilCccd.getEditText().setText(fileDTO.getCccd());
        tilPhoneNumber.getEditText().setText(fileDTO.getPhoneNumber());
        tilBirthday.getEditText().setText(formatDate2(fileDTO.getBirthday()));
        tilEmail.getEditText().setText(fileDTO.getEmail());
        tilCountry.getEditText().setText(fileDTO.getCountry());
        tilJob.getEditText().setText(fileDTO.getJob());
        if(fileDTO.getBhyt().equalsIgnoreCase("Có")){
            tilbhyt.getEditText().setText("Có bảo hiểm y tế");
        }else if(fileDTO.getBhyt().equalsIgnoreCase("Không có bảo hiểm y tế")){
            tilbhyt.getEditText().setText("Không");
        }
        tilDes.getEditText().setText(fileDTO.getDes());
    }
    public String formatDate2(String a) {
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
    private void findViewById(View view){
        imgAvata = view.findViewById(R.id.img_avata);
        tilNameFullName = view.findViewById(R.id.tilNameFullName);
        tilPhoneNumber = view.findViewById(R.id.tilPhoneNumber);
        tilBirthday = view.findViewById(R.id.tilBirthday);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilCccd = view.findViewById(R.id.tilCccd);
        tilCountry = view.findViewById(R.id.tilCountry);
        tilbhyt = view.findViewById(R.id.tilbhyt);
        tilJob = view.findViewById(R.id.tilJob);
        tilAddress = view.findViewById(R.id.tilAddress);
        tilDes = view.findViewById(R.id.tilDes);
    }
}