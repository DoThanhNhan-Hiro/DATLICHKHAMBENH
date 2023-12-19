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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.dao.TimeWorkDAO;
import com.example.datlichkham.dto.TimeWorkDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class TimeWorkAdapter extends RecyclerView.Adapter<TimeWorkAdapter.ViewHoderItemTimeWork> {
    TimeWorkDAO timeWorkDAO;
    ArrayList<TimeWorkDTO> listTimeWork;
    Context context;

    public TimeWorkAdapter(TimeWorkDAO timeWorkDAO, ArrayList<TimeWorkDTO> listTimeWork) {
        this.timeWorkDAO = timeWorkDAO;
        this.listTimeWork = listTimeWork;
    }

    @NonNull
    @Override
    public ViewHoderItemTimeWork onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_time_work, parent, false);
        context = parent.getContext();
        timeWorkDAO = new TimeWorkDAO(context);
        return new ViewHoderItemTimeWork(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderItemTimeWork holder, int position) {
        final int index = position;
        listTimeWork = timeWorkDAO.getAll();
        TimeWorkDTO _timeWorkDTO = listTimeWork.get(index);
        holder.tvTimeWork.setText(_timeWorkDTO.getSession());
        holder.tvUpdateTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddTimeWork(context, _timeWorkDTO, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTimeWork == null ? 0 : listTimeWork.size();
    }

    private TextInputLayout tilUsername;
    private AppCompatButton btnSaveShift;


    public void dialogAddTimeWork(Context context, TimeWorkDTO dtoTimeWork, int index) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_timework);
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

        edSessionTimeWork.getEditText().setText(dtoTimeWork.getSession());
        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSaveTimeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dtoTimeWork.setSession(edSessionTimeWork.getEditText().getText().toString());

                long res = timeWorkDAO.updateRow(dtoTimeWork);
                if (res > 0) {
                   listTimeWork.set(index,dtoTimeWork);
                   notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật ca làm việc thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật ca làm việc không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    public class ViewHoderItemTimeWork extends RecyclerView.ViewHolder {
        private TextView tvTimeWork;
        private TextView tvUpdateTimeWork;

        public ViewHoderItemTimeWork(@NonNull View itemView) {
            super(itemView);
            tvTimeWork = itemView.findViewById(R.id.tv_time_work);
            tvUpdateTimeWork = itemView.findViewById(R.id.tv_update_time_work);

        }
    }
}
