package com.example.datlichkham.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.DoctorViewHolder;
import com.example.datlichkham.admin.UpdateDoctorActivity;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dao.TimeWorkDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.RoomsDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.example.datlichkham.dto.TimeWorkDTO;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorViewHolder> {
    private ArrayList<DoctorDTO> listDtoDoctor = new ArrayList<>();
    private Context context;

    public DoctorAdapter(ArrayList<DoctorDTO> listDtoDoctor, Context context) {
        this.listDtoDoctor = listDtoDoctor;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor,parent,false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        DoctorDTO doctorDTO = listDtoDoctor.get(position);

        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        holder.tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameService.setText("Chuyên khoa: "+servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRoom.setText("Phòng: "+roomsDTO.getName());

        TimeWorkDAO timeWorkDAO = new TimeWorkDAO(context);
        TimeWorkDTO timeWorkDTO = timeWorkDAO.getDtoTimeWork(doctorDTO.getTimework_id());
        holder.tvTimeWork.setText("Ca làm việc: "+timeWorkDTO.getSession());

        holder.tvDes.setText(doctorDTO.getDescription());
        holder.btnUpdateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateDoctorActivity.class);
                intent.putExtra("idDoctor",doctorDTO.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDtoDoctor.size();
    }
}
