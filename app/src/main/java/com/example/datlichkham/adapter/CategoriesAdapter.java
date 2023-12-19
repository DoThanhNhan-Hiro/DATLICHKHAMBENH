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
import com.example.datlichkham.adapter.ViewHolder.CategoriesViewHolder;
import com.example.datlichkham.dao.CategoriesDAO;
import com.example.datlichkham.dto.CategoriesDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    ArrayList<CategoriesDTO> listCategoriesDto = new ArrayList<>();
    Context context;
    CategoriesDAO categoriesDAO;

    public CategoriesAdapter(ArrayList<CategoriesDTO> listCategoriesDto, Context context) {
        this.listCategoriesDto = listCategoriesDto;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cagories,parent,false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        categoriesDAO = new CategoriesDAO(context);
        CategoriesDTO categoriesDTO = listCategoriesDto.get(position);
        final int index = position;

        holder.tvNameCategories.setText(categoriesDTO.getName());
        holder.imgUpdateCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickImgUpdateCAtegories(context,categoriesDTO,index);
            }
        });
    }

    private void onClickImgUpdateCAtegories(Context context, CategoriesDTO categoriesDTO, int index) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_categories);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        } else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        TextInputLayout edNameCategories = dialog.findViewById(R.id.edNameCategories);
        Button btnSaveCategories = dialog.findViewById(R.id.btnSaveCategories);
        edNameCategories.getEditText().setText(categoriesDTO.getName());
        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSaveCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoriesDTO.setName(edNameCategories.getEditText().getText().toString());
                int res = categoriesDAO.udateRow(categoriesDTO);

                if(res>0){
                    listCategoriesDto.set(index,categoriesDTO);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật loại khám thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(context, "Cập nhật loại khám không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listCategoriesDto.size();
    }
}
