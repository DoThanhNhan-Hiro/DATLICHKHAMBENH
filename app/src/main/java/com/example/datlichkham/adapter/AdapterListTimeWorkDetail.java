package com.example.datlichkham.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.FileActivity;
import com.example.datlichkham.ItemOrderDoctorActivity;
import com.example.datlichkham.OrderDoctorActivity;
import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ViewHolder.ItemListTimeWorkDetailViewHolder;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dao.TimeWorkDetailDAO;
import com.example.datlichkham.dto.FileDTO;
import com.example.datlichkham.dto.TimeWorkDetailDTO;

import java.util.ArrayList;

public class AdapterListTimeWorkDetail extends RecyclerView.Adapter<ItemListTimeWorkDetailViewHolder> {
    private ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto = new ArrayList<>();
    private Context context;
    ItemOrderDoctorActivity itemOrderDoctorActivity;

    public AdapterListTimeWorkDetail(ArrayList<TimeWorkDetailDTO> listTimeWorkDetailDto, Context context) {
        this.listTimeWorkDetailDto = listTimeWorkDetailDto;
        this.context = context;

    }

    @NonNull
    @Override
    public ItemListTimeWorkDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_timework_detail,parent,false);
        return new ItemListTimeWorkDetailViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemListTimeWorkDetailViewHolder holder, int position) {
            TimeWorkDetailDTO timeWorkDetailDTO = listTimeWorkDetailDto.get(position);
            holder.tvNameTimeWorkDetail.setText(timeWorkDetailDTO.getTime());
            SharedPreferences preferences = context.getSharedPreferences("getIdOrderDoctor",Context.MODE_PRIVATE);
            int idDoctor = preferences.getInt("idDoctor",-1);
            String startDate = preferences.getString("startDate","");

            TimeWorkDetailDAO timeWorkDetailDAO = new TimeWorkDetailDAO(context);
            timeWorkDetailDAO.open();

            ArrayList<TimeWorkDetailDTO> timeWorkDetailDTOArrayList = timeWorkDetailDAO.listTimeWorkDetailByStartDate(startDate, idDoctor);
            for(int i=0;i<timeWorkDetailDTOArrayList.size();i++){
                if(timeWorkDetailDTOArrayList.get(i).getTime().equals(timeWorkDetailDTO.getTime())){
                    holder.itemView.setEnabled(false);
                    holder.itemView.setBackgroundResource(R.color.xam);
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences preferences1 = context.getSharedPreferences("getIdUser",Context.MODE_PRIVATE);
                    int idUser = preferences1.getInt("idUser",-1);
                    FileDAO fileDAO = new FileDAO(context);
                    ArrayList<FileDTO> listFileDto = fileDAO.getFileByIdUser(idUser);
                    if(listFileDto.size()==0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn cần phải nhập hồ sơ cá nhân trước khi đặt lịch khám");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(context,FileActivity.class);
                                    context.startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    else{
                        Intent intent = new Intent(context, OrderDoctorActivity.class);
                        intent.putExtra("idDoctor",idDoctor);
                        intent.putExtra("startTime",timeWorkDetailDTO.getTime());
                        intent.putExtra("startDate",startDate);
                        context.startActivity(intent);
                    }


                }
            });
    }
    @Override
    public int getItemCount() {
        return listTimeWorkDetailDto.size();
    }
}
