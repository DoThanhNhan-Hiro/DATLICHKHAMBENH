package com.example.datlichkham.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.TimeWorkAdapter;
import com.example.datlichkham.dao.TimeWorkDAO;
import com.example.datlichkham.dto.TimeWorkDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Fragmet_TimeWork extends Fragment {
    Context context;
    TimeWorkDAO timeWorkDAO;
    ArrayList<TimeWorkDTO> listTimeWork;
    private RecyclerView rcvListTimeWork;
    private FloatingActionButton fabAddTimeWork;
    TimeWorkAdapter adapterTimeWork;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_time_work, container, false);
        context = getContext();
        timeWorkDAO = new TimeWorkDAO(context);
        rcvListTimeWork = view.findViewById(R.id.rcv_list_time_work);
        fabAddTimeWork = view.findViewById(R.id.fab_add_time_work);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fabAddTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddTimeWork();
            }
        });
        showData();
    }
    public void dialogAddTimeWork() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_timework);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        } else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        TextInputLayout edSessionTimeWork = dialog.findViewById(R.id.edSessionTimeWork);
        Button btnSaveTimeWork = dialog.findViewById(R.id.btnSaveTimeWork);

        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSaveTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeWorkDTO _timeWorkDTO = new TimeWorkDTO();
                _timeWorkDTO.setSession(edSessionTimeWork.getEditText().getText().toString());

                long res = timeWorkDAO.insertRow(_timeWorkDTO);
                if (res > 0) {
                    listTimeWork.clear();
                    listTimeWork.addAll(timeWorkDAO.getAll());
                    adapterTimeWork.notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Thêm ca làm việc thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm ca làm việc không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        listTimeWork = timeWorkDAO.getAll();
        adapterTimeWork = new TimeWorkAdapter(timeWorkDAO, listTimeWork);
        rcvListTimeWork.setAdapter(adapterTimeWork);
    }

    public void showData() {
        listTimeWork = timeWorkDAO.getAll();
        adapterTimeWork = new TimeWorkAdapter(timeWorkDAO, listTimeWork);
        rcvListTimeWork.setAdapter(adapterTimeWork);
    }
}
