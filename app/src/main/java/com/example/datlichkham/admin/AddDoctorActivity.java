package com.example.datlichkham.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.SpinneRoomsAdapter;
import com.example.datlichkham.adapter.SpinnerServiceAdapter;
import com.example.datlichkham.adapter.SpinnerTimeWorkAdapter;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dao.TimeWorkDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.RoomsDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.example.datlichkham.dto.TimeWorkDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddDoctorActivity extends AppCompatActivity {
    private Spinner spRooms, spServices, spTimeWork;
    private SpinneRoomsAdapter spinneRoomsAdapter;
    private SpinnerServiceAdapter spinnerServiceAdapter;
    private SpinnerTimeWorkAdapter spinnerTimeWorkAdapter;
    private Button btnSaveDoctor;
    private TextInputLayout edNameDoctor, edPhoneDoctor, edNameAccount, edPassWordDoctor, edDes, edBirthdayDoctor;
    ArrayList<AccountDTO> listAccountDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        init();
        RoomsDAO roomsDAO = new RoomsDAO(this);
        ServicesDAO servicesDAO = new ServicesDAO(this);
        TimeWorkDAO timeWorkDAO = new TimeWorkDAO(this);
        AccountDAO accountDAO = new AccountDAO(this);
        DoctorDAO doctorDAO = new DoctorDAO(this);
        listAccountDoctor = accountDAO.getAccountDoctor();
        ArrayList<RoomsDTO> listDtoRooms = roomsDAO.selectAll();
        spinneRoomsAdapter = new SpinneRoomsAdapter(listDtoRooms, this);
        spRooms.setAdapter(spinneRoomsAdapter);

        ArrayList<ServicesDTO> listDtoService = servicesDAO.selectAll();
        spinnerServiceAdapter = new SpinnerServiceAdapter(listDtoService, this);
        spServices.setAdapter(spinnerServiceAdapter);

        ArrayList<TimeWorkDTO> listDtoTimeWork = timeWorkDAO.getAll();
        spinnerTimeWorkAdapter = new SpinnerTimeWorkAdapter(listDtoTimeWork, this);
        spTimeWork.setAdapter(spinnerTimeWorkAdapter);

        btnSaveDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIsempty() == true) {
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setUserName(edNameAccount.getEditText().getText().toString());
                    accountDTO.setPassWord(edPassWordDoctor.getEditText().getText().toString());
                    accountDTO.setPhoneNumber(edPhoneDoctor.getEditText().getText().toString());
                    accountDTO.setFullName(edNameDoctor.getEditText().getText().toString());
                    accountDTO.setRole("Doctor");
                    accountDTO.setImg(null);

                    long res = accountDAO.insertAccount(accountDTO);

                    AccountDTO accountDTO1 = accountDAO.getTopDtoAccount();
                    DoctorDTO doctorDTO = new DoctorDTO();
                    doctorDTO.setUser_id(accountDTO1.getId());
                    doctorDTO.setBirthday(formatDate(edBirthdayDoctor.getEditText().getText().toString()));

                    ServicesDTO servicesDTO = (ServicesDTO) spServices.getSelectedItem();
                    doctorDTO.setService_id(servicesDTO.getServicesId());

                    RoomsDTO roomsDTO = (RoomsDTO) spRooms.getSelectedItem();
                    doctorDTO.setRoom_id(roomsDTO.getId());

                    doctorDTO.setDescription(edDes.getEditText().getText().toString());

                    TimeWorkDTO timeWorkDTO = (TimeWorkDTO) spTimeWork.getSelectedItem();
                    doctorDTO.setTimework_id(timeWorkDTO.getId());

                    long res1 = doctorDAO.insertRow(doctorDTO);
                    if (res1 > 0) {
                        listAccountDoctor.clear();
                        listAccountDoctor = accountDAO.getAccountDoctor();
                        Toast.makeText(AddDoctorActivity.this, "Thêm bác sĩ thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(AddDoctorActivity.this, "Thêm bác sĩ không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean checkUserNameDoctor() {
        boolean check = true;
        for (int i = 0; i < listAccountDoctor.size(); i++) {
            if (edNameAccount.getEditText().getText().toString().equals(listAccountDoctor.get(i).getUserName())) {
                check = false;
                break;
            } else {
                check = true;
            }
        }
        return check;
    }

    public boolean checkIsempty() {
        boolean a = false;
        if (edNameAccount.getEditText().getText().toString().trim().isEmpty() || checkUserNameDoctor() == false ||
                edNameDoctor.getEditText().getText().toString().trim().isEmpty() ||
                edBirthdayDoctor.getEditText().getText().toString().trim().isEmpty() ||
                !edBirthdayDoctor.getEditText().getText().toString().matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$") ||
                edPhoneDoctor.getEditText().getText().toString().isEmpty() || !edPhoneDoctor.getEditText().getText().toString().trim().matches("^[0-9]{10}$") ||
                edPassWordDoctor.getEditText().getText().toString().trim().isEmpty() || edPassWordDoctor.getEditText().getText().toString().length() < 8){

            if (edNameAccount.getEditText().getText().toString().trim().isEmpty()) {
                edNameAccount.setError("Tên tài khoản không được để trống");
            } else if (checkUserNameDoctor() == false) {
                edNameAccount.setError("Tên tài khoản đã tồn tại");
            }
            if (edNameDoctor.getEditText().getText().toString().trim().isEmpty()) {
                edNameDoctor.setError("Họ tên không được để trống");
            }
            if (edBirthdayDoctor.getEditText().getText().toString().trim().isEmpty()) {
                edBirthdayDoctor.setError("Ngày sinh không được để trống");
            } else if (!edBirthdayDoctor.getEditText().getText().toString().matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$")) {
                edBirthdayDoctor.setError("Ngày sinh không đúng định dạng");
            }
            if (edPhoneDoctor.getEditText().getText().toString().isEmpty()) {
                edPhoneDoctor.setError("Số điện thoại không được để trống");
            } else if (!edPhoneDoctor.getEditText().getText().toString().trim().matches("^[0-9]{10}$")) {
                edPhoneDoctor.setError("Số điện thoại phải 10 chữ số");
            }
            if (edPassWordDoctor.getEditText().getText().toString().trim().isEmpty()) {
                edPassWordDoctor.setError("Mật khẩu không được để trống");
            } else if (edPassWordDoctor.getEditText().getText().toString().length() < 8) {
                edPassWordDoctor.setError("Mật khẩu ít nhất là 8 ký tự");
            }
            return false;
        }else{
            edNameAccount.setError("");
            edNameDoctor.setError("");
            edBirthdayDoctor.setError("");
            edPhoneDoctor.setError("");
            edPassWordDoctor.setError("");
            return true;
        }



}

    public void init() {
        spRooms = findViewById(R.id.spRooms);
        spServices = findViewById(R.id.spServices);
        spTimeWork = findViewById(R.id.spTimeWork);
        btnSaveDoctor = findViewById(R.id.btnSaveDoctor);
        edNameDoctor = findViewById(R.id.edNameDoctor);
        edPhoneDoctor = findViewById(R.id.edPhoneDoctor);
        edNameAccount = findViewById(R.id.edNameAccount);
        edPassWordDoctor = findViewById(R.id.edPassWordDoctor);
        edDes = findViewById(R.id.edDes);
        edBirthdayDoctor = findViewById(R.id.edBirthdayDoctor);
    }

    public String formatDate(String a) {
        String newDate = "";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 = a;
        SimpleDateFormat Format2 = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("yyyy/mm/dd", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }
}