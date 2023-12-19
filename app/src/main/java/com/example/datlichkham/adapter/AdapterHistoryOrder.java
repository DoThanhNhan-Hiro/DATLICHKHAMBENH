package com.example.datlichkham.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.ItemHistoryOrderViewHolder;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.DoctorDAO;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dao.OrderDAO;
import com.example.datlichkham.dao.OrderDoctorDAO;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.FileDTO;
import com.example.datlichkham.dto.OrderDTO;
import com.example.datlichkham.dto.OrderDetailDTO;
import com.example.datlichkham.dto.OrderDoctorDTO;
import com.example.datlichkham.dto.RoomsDTO;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;

public class AdapterHistoryOrder extends RecyclerView.Adapter<ItemHistoryOrderViewHolder> {
    private ArrayList<OrderDetailDTO> listOrderDetai = new ArrayList<>();
    private Context context;

    public AdapterHistoryOrder(ArrayList<OrderDetailDTO> listOrderDetai, Context context) {
        this.listOrderDetai = listOrderDetai;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHistoryOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_history, parent, false);
        return new ItemHistoryOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHistoryOrderViewHolder holder, int position) {
        OrderDetailDTO orderDetailDTO = listOrderDetai.get(position);
        holder.tvOrderId.setText("Mã đơn hàng: " + orderDetailDTO.getOrder_id());

        OrderDoctorDAO orderDoctorDAO = new OrderDoctorDAO(context);
        OrderDoctorDTO orderDoctorDTO = orderDoctorDAO.getOrderDoctorDtoById(orderDetailDTO.getOrderDoctor_id());

        FileDAO fileDAO = new FileDAO(context);
        FileDTO fileDTO = fileDAO.getFileDToById(orderDoctorDTO.getFile_id());
        holder.tvNamefile.setText(fileDTO.getFullname());

        DoctorDAO doctorDAO = new DoctorDAO(context);
        DoctorDTO doctorDTO = doctorDAO.getDtoDoctorByIdDoctor(orderDoctorDTO.getDoctor_id());

        AccountDAO accountDAO = new AccountDAO(context);
        AccountDTO accountDTO = accountDAO.getDtoAccount(doctorDTO.getUser_id());
        holder.tvNameDoctor.setText(accountDTO.getFullName());

        ServicesDAO servicesDAO = new ServicesDAO(context);
        ServicesDTO servicesDTO = servicesDAO.getDtoServiceByIdByService(doctorDTO.getService_id());
        holder.tvNameService.setText(servicesDTO.getServicesName());

        RoomsDAO roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = roomsDAO.getDtoRoomByIdRoom(doctorDTO.getRoom_id());
        holder.tvNameRooms.setText(roomsDTO.getName());

        holder.tvStartDate.setText(orderDoctorDTO.getStart_date());
        holder.tvStartTime.setText(orderDoctorDTO.getStart_time());
        OrderDAO orderDAO = new OrderDAO(context);
        OrderDTO orderDTO = orderDAO.getOrderDTOById(orderDetailDTO.getOrder_id());
        holder.tvOrderDate.setText("Ngày đăt: "+orderDTO.getOrder_date());
        holder.tvOrderTime.setText("Giờ đặt: "+orderDTO.getOrder_time());
        holder.tvNote.setText(orderDTO.getNote());
        holder.tvPrice.setText(servicesDTO.getServicesPrice()+"đ");
        holder.tvStatusOrder.setText(orderDTO.getStatus());
    }

    @Override
    public int getItemCount() {
        return listOrderDetai.size();
    }
}
