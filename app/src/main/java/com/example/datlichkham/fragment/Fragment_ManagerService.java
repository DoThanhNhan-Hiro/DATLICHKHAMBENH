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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.datlichkham.R;
import com.example.datlichkham.adapter.ServicesAdapter;
import com.example.datlichkham.adapter.SpinnerCategoriesAdapter;
import com.example.datlichkham.dao.CategoriesDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.CategoriesDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Fragment_ManagerService extends Fragment {
    RecyclerView rcvServices;
    FloatingActionButton fabAddServices;
    ServicesDAO servicesDAO;
    CategoriesDAO categoriesDAO;
    ArrayList<ServicesDTO> list;
    ServicesAdapter servicesAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manager_service, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvServices = view.findViewById(R.id.rcv_list_services);
        fabAddServices = view.findViewById(R.id.fab_add_services);

        categoriesDAO = new CategoriesDAO(getContext());
        servicesDAO = new ServicesDAO(getContext());

        list = new ArrayList<>();
        list = servicesDAO.selectAll();

        servicesAdapter = new ServicesAdapter(getContext(), list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvServices.setLayoutManager(layoutManager);
        rcvServices.setAdapter(servicesAdapter);

        fabAddServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogAddServices();
            }
        });
    }

    private void openDialogAddServices() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_service);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        } else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        TextInputLayout edNameService = (TextInputLayout) dialog.findViewById(R.id.edNameService);
        TextInputLayout edPriceService = (TextInputLayout) dialog.findViewById(R.id.edPriceService);
        Spinner spCategories = (Spinner) dialog.findViewById(R.id.spCategories);
        Button btnSaveService = (Button) dialog.findViewById(R.id.btnSaveService);
        ImageView imgCancel = (ImageView) dialog.findViewById(R.id.img_cancel);

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ArrayList<CategoriesDTO> listCategories = categoriesDAO.selectAll();
        SpinnerCategoriesAdapter spinnerCategoriesAdapter = new SpinnerCategoriesAdapter(listCategories, getContext());
        spCategories.setAdapter(spinnerCategoriesAdapter);

        btnSaveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServicesDTO servicesDTO = new ServicesDTO();
                servicesDTO.setServicesName(edNameService.getEditText().getText().toString());
                servicesDTO.setServicesPrice(Float.parseFloat(edPriceService.getEditText().getText().toString()));
                CategoriesDTO categoriesDTO = (CategoriesDTO) spCategories.getSelectedItem();
                servicesDTO.setCategoriesId(categoriesDTO.getId());

                if (servicesDAO.insertServices(servicesDTO) > 0) {
                    list.clear();
                    list.addAll(servicesDAO.selectAll());
                    servicesAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}