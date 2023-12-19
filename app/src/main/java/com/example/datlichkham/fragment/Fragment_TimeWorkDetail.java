package com.example.datlichkham.fragment;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.SpinnerTimeWorkAdapter;
import com.example.datlichkham.adapter.TimeWorkDetailAdapter;
import com.example.datlichkham.dao.TimeWorkDAO;
import com.example.datlichkham.dao.TimeWorkDetailDAO;
import com.example.datlichkham.dto.TimeWorkDTO;
import com.example.datlichkham.dto.TimeWorkDetailDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Fragment_TimeWorkDetail extends Fragment {
    Context context;
    private TimeWorkDAO daoTimeWork;
    private TimeWorkDetailDAO daoTimeWorkDetail;
    private TimeWorkDetailAdapter timeWorkDetailAdapter;
    private ArrayList<TimeWorkDetailDTO> listDtoTimeWorkDetail;
    private RecyclerView rcvListTimeWorkDetail;
    private FloatingActionButton fabAddTimeWorkDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_work_detail, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        rcvListTimeWorkDetail = view.findViewById(R.id.rcv_list_time_work_detail);
        fabAddTimeWorkDetail = view.findViewById(R.id.fab_add_time_work_detail);


        //Khởi tạo
        daoTimeWork = new TimeWorkDAO(context);
        //Mở cơ sở dữ liệu
        daoTimeWork.open();

        //Khởi tạo
        daoTimeWorkDetail = new TimeWorkDetailDAO(context);
        //Mở cơ sở dữ liệu
        daoTimeWorkDetail.open();

        //Lấy danh sách timeworkdetail
        listDtoTimeWorkDetail = daoTimeWorkDetail.selectAll();
        TimeWorkDetailAdapter timeWorkDetailAdapter = new TimeWorkDetailAdapter(listDtoTimeWorkDetail, context);
        LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rcvListTimeWorkDetail.setLayoutManager(manager);
        rcvListTimeWorkDetail.setAdapter(timeWorkDetailAdapter);

        fabAddTimeWorkDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_timework_detail);
                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                } else {
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                Spinner spTimeWork = dialog.findViewById(R.id.spTimeWork);
                ArrayList<TimeWorkDTO> listTimeWork = daoTimeWork.getAll();
                SpinnerTimeWorkAdapter timeWorkAdapter = new SpinnerTimeWorkAdapter(listTimeWork, context);
                spTimeWork.setAdapter(timeWorkAdapter);

                ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
                imgCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                TextInputLayout edTimeWorkDetail = dialog.findViewById(R.id.edTimeWorkDetail);
                Button btnSaveTimeWorkDetail = dialog.findViewById(R.id.btnSaveTimeWorkDetail);
                //Bắt sự kiện cho nút save room
                btnSaveTimeWorkDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edTimeWorkDetail.getEditText().getText().toString().isEmpty()) {
                            edTimeWorkDetail.setError("Hours is not isEmpty");
                        } else if (!edTimeWorkDetail.getEditText().getText().toString().matches("^[0-9]:[0-6][0-9]-[0-9]:[0-6][0-9]$")) {
                            edTimeWorkDetail.setError("Incorrect format h:mm-h:mm");
                        } else {
                            TimeWorkDetailDTO dtoTimeWorkDetail = new TimeWorkDetailDTO();
                            dtoTimeWorkDetail.setTime(edTimeWorkDetail.getEditText().getText().toString());
                            TimeWorkDTO timeWorkDTO = (TimeWorkDTO) spTimeWork.getSelectedItem();
                            dtoTimeWorkDetail.setTimework_id(timeWorkDTO.getId());

                            long res = daoTimeWorkDetail.insertRow(dtoTimeWorkDetail);
                            if (res > 0) {
                                listDtoTimeWorkDetail.clear();
                                listDtoTimeWorkDetail.addAll(daoTimeWorkDetail.selectAll());
                                timeWorkDetailAdapter.notifyDataSetChanged();
                                Toast.makeText(context, "Thêm giờ làm thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(context, "Thêm giờ làm không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                });
                dialog.show();
            }
        });
    }

}