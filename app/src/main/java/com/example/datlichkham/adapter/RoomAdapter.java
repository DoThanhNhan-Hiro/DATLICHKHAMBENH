package com.example.datlichkham.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.RoomViewHolder;
import com.example.datlichkham.dao.RoomsDAO;
import com.example.datlichkham.dto.RoomsDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    private ArrayList<RoomsDTO> listRooms = new ArrayList<>();
    private Context context;
    RoomsDAO roomsDAO;

    public RoomAdapter(ArrayList<RoomsDTO> listRooms, Context context) {
        this.listRooms = listRooms;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rooms, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        roomsDAO = new RoomsDAO(context);
        RoomsDTO roomsDTO = listRooms.get(position);
        holder.tvNameRoom.setText(roomsDTO.getName());
        holder.tvLocationRoom.setText(roomsDTO.getLocation());

        final int index = position;

        holder.imgUpdateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpdateRoom(context, roomsDTO, index);
            }
        });
    }

    private void openDialogUpdateRoom(Context context, RoomsDTO roomsDTO, int index) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_rooms);
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

        edNameRoom.getEditText().setText(roomsDTO.getName());
        edLocation.getEditText().setText(roomsDTO.getLocation());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomsDTO.setName(edNameRoom.getEditText().getText().toString().trim());
                roomsDTO.setLocation(edLocation.getEditText().getText().toString().trim());

                if (roomsDAO.updateRow(roomsDTO) > 0) {
                    listRooms.set(index, roomsDTO);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listRooms.size();
    }
}
