package com.example.datlichkham.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.DoctorOrderViewHolder;
import com.example.datlichkham.dao.AccountDAO;
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

public class AdapterDoctorOrder extends RecyclerView.Adapter<DoctorOrderViewHolder> {
    private ArrayList<DoctorDTO> listDtoDoctor = new ArrayList<>();
    private Context context;

    public AdapterDoctorOrder(ArrayList<DoctorDTO> listDtoDoctor, Context context) {
        this.listDtoDoctor = listDtoDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_doctor, parent, false);
        return new DoctorOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorOrderViewHolder holder, int position) {
        holder.imgIconOrderDate.setVisibility(View.GONE);
        holder.tvOrderDate.setVisibility(View.GONE);
        holder.tvTimeWork.setVisibility(View.GONE);
        holder.tvClickOrder.setVisibility(View.GONE);
        DoctorDTO doctorDTO = listDtoDoctor.get(position);
        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        holder.tvNameDoctor.setText(accountDTO.getFullName());

        holder.tvDes.setText(doctorDTO.getDescription());

        TimeWorkDAO timeWorkDAO = new TimeWorkDAO(context);
        TimeWorkDTO timeWorkDTO = timeWorkDAO.getDtoTimeWork(doctorDTO.getTimework_id());
        holder.tvTimeWork.setText(timeWorkDTO.getSession());

        TimeWorkDetailDAO timeWorkDetailDAO = new TimeWorkDetailDAO(context);
        timeWorkDetailDAO.open();

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameSerivce.setText(servicesDTO.getServicesName());
        holder.tvPrice.setText(servicesDTO.getServicesPrice() + "đ");

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRoom.setText(roomsDTO.getName());
        holder.tvDes.setText(doctorDTO.getDescription());

        SharedPreferences sharedPreferences = context.getSharedPreferences("getIdOrderDoctor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("idDoctor", doctorDTO.getId());
        holder.tvBirthdayOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = day + "/" + (month + 1) + "/" + year;
                        holder.tvBirthdayOrder.setText(date);
                        editor.putString("startDate", formatDate(date));
                        editor.commit();
                        ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto = timeWorkDetailDAO.selectTimeWorkDetailByTimeWorkId(timeWorkDTO.getId());
                        AdapterListTimeWorkDetail adapterListTimeWorkDetail = new AdapterListTimeWorkDetail(listTimeWorkDetailDto, context);
                        LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
                        holder.rcv_list_timework_detail.setLayoutManager(manager);
                        holder.rcv_list_timework_detail.setAdapter(adapterListTimeWorkDetail);
                        holder.imgIconOrderDate.setVisibility(View.VISIBLE);
                        holder.tvOrderDate.setVisibility(View.VISIBLE);
                        holder.tvClickOrder.setVisibility(View.VISIBLE);
                        holder.tvTimeWork.setVisibility(View.VISIBLE);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDtoDoctor.size();
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
}
