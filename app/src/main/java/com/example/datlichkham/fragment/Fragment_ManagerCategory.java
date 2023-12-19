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
import com.example.datlichkham.adapter.CategoriesAdapter;
import com.example.datlichkham.dao.CategoriesDAO;
import com.example.datlichkham.dto.CategoriesDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Fragment_ManagerCategory extends Fragment {
    private RecyclerView rcv_list_category;
    private FloatingActionButton fab_add_category;
    private CategoriesDAO categoriesDAO;
    private CategoriesAdapter categoriesAdapter;
    private ArrayList<CategoriesDTO> listCategoriesDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__manager_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_category = view.findViewById(R.id.rcv_list_category);
        fab_add_category = view.findViewById(R.id.fab_add_category);
        categoriesDAO = new CategoriesDAO(getContext());

        listCategoriesDTO = categoriesDAO.selectAll();
        categoriesAdapter = new CategoriesAdapter(listCategoriesDTO, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_list_category.setLayoutManager(manager);
        rcv_list_category.setAdapter(categoriesAdapter);

        fab_add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_categories);
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

                img_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnSaveCategories.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CategoriesDTO categoriesDTO = new CategoriesDTO();
                        categoriesDTO.setName(edNameCategories.getEditText().getText().toString());

                        long res = categoriesDAO.insertRow(categoriesDTO);
                        if (res > 0) {
                            listCategoriesDTO.clear();
                            listCategoriesDTO.addAll(categoriesDAO.selectAll());
                            categoriesAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Thêm loại khám thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Thêm loại khám không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        listCategoriesDTO = categoriesDAO.selectAll();
        categoriesAdapter = new CategoriesAdapter(listCategoriesDTO, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_list_category.setLayoutManager(manager);
        rcv_list_category.setAdapter(categoriesAdapter);
    }
}