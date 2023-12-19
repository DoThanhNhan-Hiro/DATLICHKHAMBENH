package com.example.datlichkham.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.OrderDetailDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.AllDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;
import com.example.datlichkham.dto.RoomsDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterUpdateDoctorInOrder extends RecyclerView.Adapter<AdapterUpdateDoctorInOrder.ViewHoderItemUpdateDoctorInOrder> {
    OrderDoctorDTO orderDoctorDTO;
    SpinnerDoctorAdapter spinnerDoctorAdapter;
    ArrayList<DoctorDTO> listDoctor;
    OrderDetailDAO orderDetailDAO;
    ArrayList<AllDTO> listOrder;
    Context context;
    AllDTO allDTO;
    DoctorDAO doctorDAO;
    RoomsDAO roomsDAO;
    RoomsDTO roomsDTO;
    ServicesDAO servicesDAO;
    ServicesDTO servicesDTO;
    OrderDoctorDAO orderDoctorDAO;
    String TAG = "zzzzzzzzzzzzzzzzz";
    String check;
    int idFile;


    public AdapterUpdateDoctorInOrder(ArrayList<AllDTO> listOrder, String check, int idFile) {
        this.listOrder = listOrder;
        this.check = check;
        this.idFile = idFile;
    }

    @NonNull
    @Override
    public ViewHoderItemUpdateDoctorInOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_order_doctor2, parent, false);
        context = parent.getContext();
        orderDetailDAO = new OrderDetailDAO(context);
        doctorDAO = new DoctorDAO(context);
        roomsDAO = new RoomsDAO(context);
        servicesDAO = new ServicesDAO(context);
        orderDoctorDAO = new OrderDoctorDAO(context);
        return new ViewHoderItemUpdateDoctorInOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderItemUpdateDoctorInOrder holder, int position) {
        final int index = position;
        allDTO = listOrder.get(index);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(allDTO.getIdDoctor());
        listDoctor = doctorDAO.getListDoctorByTimeWord(allDTO.getStartTime(), doctorDTO.getService_id(), doctorDTO.getTimework_id(), allDTO.getStartDate());
        listDoctor.add(doctorDTO);
        spinnerDoctorAdapter = new SpinnerDoctorAdapter(listDoctor, context);
        holder.tvfullName.setText(allDTO.getFullameUser());
        holder.spNameDoctor.setAdapter(spinnerDoctorAdapter);
        for (int i = 0; i < listDoctor.size(); i++) {
            if (allDTO.getIdDoctor() == listDoctor.get(i).getId()) {
                holder.spNameDoctor.setSelected(true);
                holder.spNameDoctor.setSelection(i);
            }
        }
        Log.e(TAG, "onBindViewHolder: order_id:"+allDTO.getOrder_id() );
        Log.e(TAG, "onBindViewHolder: orderDoctor_id:"+allDTO.getOrderDoctor_id() );
        servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameService.setText(servicesDTO.getServicesName());
        holder.tvNameRooms.setText(roomsDTO.getName());
        holder.tv_Id.setText("Mã lịch khám: "+listOrder.get(index).getOrder_id()+"");
        holder.tv_Id.setVisibility(View.GONE);

        holder.tvStartDate.setText(formatDate(allDTO.getStartDate()));
        holder.tvStartTime.setText(allDTO.getStartTime());
        holder.spNameDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (check.equalsIgnoreCase("nocomfrim")) {
            holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(listOrder.get(index).getOrderDoctor_id());
                    dialogupdate2(holder.spNameDoctor, index, orderDoctorDTO);

                    

                }
            });
            holder.llNote.setVisibility(View.GONE);
        } else if (check.equalsIgnoreCase("yescomfrim")) {
            holder.btnUpdate.setVisibility(View.INVISIBLE);
            holder.llNote.setVisibility(View.VISIBLE);
            holder.tvNote.setText(allDTO.getNote());
            holder.spNameDoctor.setEnabled(false);
        }


    }

    @Override
    public int getItemCount() {
        return listOrder == null ? 0 : listOrder.size();
    }

    public String formatDate(String a) {
        String newDate = "";
        Date objdate2 = new Date(System.currentTimeMillis());
        DateFormat dateFormat2 = new DateFormat();
        String dates2 = a;
        SimpleDateFormat Format2 = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date obj = Format2.parse(dates2);
            newDate = (String) dateFormat2.format("dd/mm/yyyy", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public void dialogupdate2(Spinner spinner, int index, OrderDoctorDTO obj) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cập nhật");
        builder.setMessage("Cập nhật thành công ");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DoctorDTO doctorDTO = (DoctorDTO) spinner.getSelectedItem();
                Log.e(TAG, "onClick: " + doctorDTO.getId());
//                Toast.makeText(context, "idOrderDoctor"+listOrder.get(index).getOrderDoctor_id() , Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, "idOrderDoctor"+obj.getId() , Toast.LENGTH_SHORT).show();
                obj.setDoctor_id(doctorDTO.getId());
                int res = orderDoctorDAO.updateRow(obj);
                if (res > 0) {
                    notifyDataSetChanged();
                    listOrder.clear();
                    listDoctor.clear();
                    orderDetailDAO = new OrderDetailDAO(context);
                    listOrder = orderDetailDAO.getListOrderByIdFile(idFile);
                    listDoctor = doctorDAO.getListDoctorByTimeWord(allDTO.getStartTime(), doctorDTO.getService_id(), doctorDTO.getTimework_id(), allDTO.getStartDate());
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public class ViewHoderItemUpdateDoctorInOrder extends RecyclerView.ViewHolder {
        private TextView tvfullName;
        private Spinner spNameDoctor;
        private TextView tvNameService;
        private TextView tvNameRooms;
        private TextView tvStartDate;
        private TextView tvStartTime;
        private MaterialButton btnUpdate;
        private LinearLayout llNote;
        private TextView tvNote;
        private TextView tv_Id;

        public ViewHoderItemUpdateDoctorInOrder(@NonNull View itemView) {
            super(itemView);
            tvfullName = itemView.findViewById(R.id.tvfullName);
            spNameDoctor = itemView.findViewById(R.id.spNameDoctor);
            tvNameService = itemView.findViewById(R.id.tvNameService);
            tvNameRooms = itemView.findViewById(R.id.tvNameRooms);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            llNote = itemView.findViewById(R.id.ll_note);
            tvNote = itemView.findViewById(R.id.tvNote);
            tv_Id = itemView.findViewById(R.id.tv_id);
        }
    }
}
