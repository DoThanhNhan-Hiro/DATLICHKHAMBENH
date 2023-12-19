package com.example.datlichkham;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.FileDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FileActivity extends AppCompatActivity {
    private TextInputLayout tilNameFullName,tilEmail,tilCccd,tilCountry,tilJob,tilAddress,tilBirthday;
    private RadioButton rdoYes,rdoNo;
    private ImageView imgBirthday;
    private Button btnSave;
    private TextInputLayout tilPhoneNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        init();
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        ImageView img_open_back = toolbar1.findViewById(R.id.img_open_back);
        img_open_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        SharedPreferences preferences = getSharedPreferences("getIdUser",MODE_PRIVATE);
        int idUser = preferences.getInt("idUser",-1);

        FileDAO fileDAO = new FileDAO(FileActivity.this);
        ArrayList<FileDTO> listFileDto = fileDAO.getFileByIdUser(idUser);
        AccountDAO accountDAO = new AccountDAO(getApplicationContext());
        AccountDTO accountDTO = accountDAO.getDtoAccount(idUser);
        if(listFileDto.size() != 0){
            FileDTO fileDTO = listFileDto.get(0);
            tilAddress.getEditText().setText(fileDTO.getAddress());
            tilCccd.getEditText().setText(fileDTO.getCccd());
            tilCountry.getEditText().setText(fileDTO.getCountry());
            tilEmail.getEditText().setText(fileDTO.getEmail());
            tilJob.getEditText().setText(fileDTO.getJob());
            tilNameFullName.getEditText().setText(fileDTO.getFullname());
            tilPhoneNumber.getEditText().setText(accountDTO.getPhoneNumber());
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            try {
                Date d = f.parse(fileDTO.getBirthday());
                SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
                tilBirthday.getEditText().setText(f1.format(d));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(fileDTO.getBhyt().equals("Không")){
                rdoNo.setChecked(true);
            }
            else{
                rdoYes.setChecked(true);
            }
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tilNameFullName.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Họ tên không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilBirthday.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Ngày sinh không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilEmail.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Email không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilCccd.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Thẻ căn cước công dân", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilCountry.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Quốc tịch không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilJob.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Công việc không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilAddress.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Địa chỉ không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilPhoneNumber.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Số điện thoại không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    fileDTO.setFullname(tilNameFullName.getEditText().getText().toString());
                    fileDTO.setUser_id(idUser);
                    fileDTO.setBirthday(formatDate(tilBirthday.getEditText().getText().toString()));
                    fileDTO.setCccd(tilCccd.getEditText().getText().toString());
                    fileDTO.setCountry(tilCountry.getEditText().getText().toString());
                    fileDTO.setPhoneNumber(tilPhoneNumber.getEditText().getText().toString());
                    if(rdoYes.isChecked()){
                        fileDTO.setBhyt("Có");
                    }
                    else{
                        fileDTO.setBhyt("Không");
                    }
                    fileDTO.setJob(tilJob.getEditText().getText().toString());
                    fileDTO.setEmail(tilEmail.getEditText().getText().toString());
                    fileDTO.setAddress(tilAddress.getEditText().getText().toString());
                    accountDTO.setPhoneNumber(tilPhoneNumber.getEditText().getText().toString().trim());
                    int res = fileDAO.updateRow(fileDTO);
                    if(res>0){

                        Toast.makeText(FileActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            rdoNo.setChecked(true);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tilNameFullName.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Họ tên không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilBirthday.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Ngày sinh không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilEmail.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Email không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilCccd.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Thẻ căn cước công dân", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilCountry.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Quốc tịch không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilJob.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Công việc không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(tilAddress.getEditText().getText().toString().equals("")){
                        Toast.makeText(FileActivity.this, "Địa chỉ không được bỏ trống", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setFullname(tilNameFullName.getEditText().getText().toString());
                    fileDTO.setUser_id(idUser);
                    fileDTO.setBirthday(formatDate(tilBirthday.getEditText().getText().toString()));
                    fileDTO.setCccd(tilCccd.getEditText().getText().toString());
                    fileDTO.setCountry(tilCountry.getEditText().getText().toString());
                    if(rdoYes.isChecked()){
                        fileDTO.setBhyt("Có");
                    }
                    else{
                        fileDTO.setBhyt("Không");
                    }
                    fileDTO.setJob(tilJob.getEditText().getText().toString());
                    fileDTO.setEmail(tilEmail.getEditText().getText().toString());
                    fileDTO.setAddress(tilAddress.getEditText().getText().toString());
                    fileDTO.setPhoneNumber(tilPhoneNumber.getEditText().getText().toString());
                    long res = fileDAO.insertRow(fileDTO);
                    if(res>0){
                        Toast.makeText(FileActivity.this, "Lưu hồ sơ thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        tilBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(FileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = day+"/"+(month+1)+"/"+year;
                        tilBirthday.getEditText().setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });



    }
    public void init(){
        tilNameFullName = findViewById(R.id.tilNameFullName);
        tilEmail = findViewById(R.id.tilEmail);
        tilCccd  =findViewById(R.id.tilCccd);
        tilCountry = findViewById(R.id.tilCountry);
        tilJob = findViewById(R.id.tilJob);
        tilAddress = findViewById(R.id.tilAddress);
        rdoYes = findViewById(R.id.rdoYes);
        rdoNo = findViewById(R.id.rdoNo);
        tilBirthday = findViewById(R.id.tilBirthday);
        btnSave = findViewById(R.id.btnSave);
        tilPhoneNumber = findViewById(R.id.tilPhoneNumber);
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