package com.example.datlichkham.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.ItemFileDetailActivity;
import com.example.datlichkham.R;
import com.example.datlichkham.ViewPagerItemFileDetailActivity;
import com.example.datlichkham.dao.AccountDAO;
import com.example.datlichkham.dao.FileDAO;
import com.example.datlichkham.dto.AccountDTO;
import com.example.datlichkham.dto.FileDTO;

import java.util.ArrayList;

public class AdapterListFile extends RecyclerView.Adapter<AdapterListFile.ViewHoderItemListFile> {
    ArrayList<FileDTO> listFile;
    FileDAO fileDAO;
    Context context;
    AccountDAO accountDAO;
    String check;

    public AdapterListFile(ArrayList<FileDTO> listFile, FileDAO fileDAO, String check) {
        this.listFile = listFile;
        this.fileDAO = fileDAO;
        this.check = check;
    }

    @NonNull
    @Override
    public ViewHoderItemListFile onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.item_list_file, parent, false);
        context = parent.getContext();
        fileDAO = new FileDAO(context);
        accountDAO = new AccountDAO(context);
        return new ViewHoderItemListFile(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderItemListFile holder, int position) {
        final int index = position;
        FileDTO fileDTO = listFile.get(index);
        AccountDTO accountDTO = accountDAO.getDtoAccount(fileDTO.getUser_id());
        holder.tvFullName.setText(fileDTO.getFullname());
        holder.tvAddress.setText(fileDTO.getAddress());
        holder.tvPhoneNumber.setText(fileDTO.getPhoneNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check.equalsIgnoreCase("user")){
                    Intent intent = new Intent(context, ItemFileDetailActivity.class);
                    intent.putExtra("id",fileDTO.getId());
                    intent.putExtra("idUser",fileDTO.getUser_id());
                    context.startActivity(intent);
                }else if(check.equalsIgnoreCase("admin")){
                    Intent intent = new Intent(context, ViewPagerItemFileDetailActivity.class);
                    context.startActivity(intent);
                    SharedPreferences sharedPreferences = context.getSharedPreferences("idFile",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.putInt("idFile",fileDTO.getId());
                    editor.putInt("id_user",fileDTO.getUser_id());
                    editor.commit();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listFile==null?0: listFile.size();
    }

    public class ViewHoderItemListFile extends RecyclerView.ViewHolder {
        private TextView tvFullName;
        private TextView tvAddress;
        private TextView tvPhoneNumber;
        public ViewHoderItemListFile(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tv_fullName);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPhoneNumber = itemView.findViewById(R.id.tv_phoneNumber);

        }
    }
}
