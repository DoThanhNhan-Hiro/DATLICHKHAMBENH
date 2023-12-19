package com.example.datlichkham.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.RoomAdapter;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dto.RoomsDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Fragment_ManagerRoom extends Fragment {
    private RecyclerView rcv_list_rooms;
    private FloatingActionButton fab_add_room;
    private RoomsDAO roomsDAO;
    private ArrayList<RoomsDTO> listRoomsDto;
    private RoomAdapter roomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__manager_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_rooms = view.findViewById(R.id.rcv_list_rooms);
        fab_add_room = view.findViewById(R.id.fab_add_room);

        roomsDAO = new RoomsDAO(getContext());

        fab_add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFloatButton();
            }
        });
        listRoomsDto = roomsDAO.selectAll();
        roomAdapter = new RoomAdapter(listRoomsDto, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_list_rooms.setLayoutManager(manager);
        rcv_list_rooms.setAdapter(roomAdapter);
    }

    private void onClickFloatButton() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_rooms);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        } else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        TextInputLayout edNameRoom = dialog.findViewById(R.id.edNameRoom);
        TextInputLayout edLocation = dialog.findViewById(R.id.edLocation);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomsDTO roomsDTO = new RoomsDTO();
                roomsDTO.setName(edNameRoom.getEditText().getText().toString());
                roomsDTO.setLocation(edLocation.getEditText().getText().toString());

                long res = roomsDAO.insertRow(roomsDTO);
                if (res > 0) {
                    listRoomsDto.clear();
                    listRoomsDto.addAll(roomsDAO.selectAll());
                    roomAdapter.notifyDataSetChanged();
//                    dialog.dismiss();
                    Toast.makeText(getContext(), "Thêm phòng khám thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm phòng khám không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        listRoomsDto = roomsDAO.selectAll();
        roomAdapter = new RoomAdapter(listRoomsDto, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_list_rooms.setLayoutManager(manager);
        rcv_list_rooms.setAdapter(roomAdapter);
    }
}