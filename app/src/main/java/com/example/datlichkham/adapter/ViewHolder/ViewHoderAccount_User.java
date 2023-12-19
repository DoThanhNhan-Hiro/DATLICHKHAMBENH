package com.example.datlichkham.adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHoderAccount_User extends RecyclerView.ViewHolder {
    public CircleImageView imgUser;
    public TextView tvFullName;
    public TextView tvUserName;
    public TextView tvPassWord;
    public TextView tvPhoneNumber;



    public ViewHoderAccount_User(@NonNull View itemView) {
        super(itemView);
        imgUser = itemView.findViewById(R.id.imgManagerUser);
        tvFullName = itemView.findViewById(R.id.tvFullName);
        tvUserName = itemView.findViewById(R.id.tvUserName);
        tvPassWord = itemView.findViewById(R.id.tvPassWord);
        tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
    }


}
