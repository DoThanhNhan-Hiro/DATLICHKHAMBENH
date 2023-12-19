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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkham.R;
import com.example.datlichkham.dao.CategoriesDAO;
import com.example.datlichkham.dao.ServicesDAO;
import com.example.datlichkham.dto.CategoriesDTO;
import com.example.datlichkham.dto.ServicesDTO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {
    Context context;
    ArrayList<ServicesDTO> list;
    ServicesDAO servicesDAO;
    CategoriesDAO categoriesDAO;

    public ServicesAdapter(Context context, ArrayList<ServicesDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {
        servicesDAO = new ServicesDAO(context);
        categoriesDAO = new CategoriesDAO(context);

        final int index = position;
        ServicesDTO servicesDTO = list.get(position);
        holder.tvNameService.setText(servicesDTO.getServicesName());

        CategoriesDTO categoriesDTO = categoriesDAO.getDtoCategories(servicesDTO.getCategoriesId());
        holder.tvCategories.setText(categoriesDTO.getName());

//        holder.tvDeletService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (servicesDAO.deleteServices(servicesDTO.getServicesId()) > 0) {
//                    list.clear();
//                    list.addAll(servicesDAO.selectAll());
//                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                    notifyDataSetChanged();
//                } else {
//                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        holder.tvUpdateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpdateServices(context, servicesDTO, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void openDialogUpdateServices(Context context, ServicesDTO servicesDTO, int index) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_service);
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
        SpinnerCategoriesAdapter spinnerCategoriesAdapter = new SpinnerCategoriesAdapter(listCategories, context);
        spCategories.setAdapter(spinnerCategoriesAdapter);

        for (int i = 0; i < listCategories.size(); i++) {
            CategoriesDTO categoriesDTO = listCategories.get(i);
            if (categoriesDTO.getId() == (servicesDTO.getCategoriesId())) {
                spCategories.setSelected(true);
                spCategories.setSelection(i);
            }
        }

        edNameService.getEditText().setText(servicesDTO.getServicesName());
        edPriceService.getEditText().setText(String.valueOf(servicesDTO.getServicesPrice()));

        btnSaveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesDTO.setServicesName(edNameService.getEditText().getText().toString());
                servicesDTO.setServicesPrice(Float.parseFloat(edPriceService.getEditText().getText().toString()));
                CategoriesDTO categoriesDTO = (CategoriesDTO) spCategories.getSelectedItem();
                servicesDTO.setCategoriesId(categoriesDTO.getId());

                if (servicesDAO.updateServices(servicesDTO) > 0){
                    list.set(index, servicesDTO);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    public static class ServicesViewHolder extends RecyclerView.ViewHolder {
        ImageView imgService;
        TextView tvNameService;
        TextView tvDeletService;
        TextView tvCategories;
        ImageView tvUpdateService;

        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgService = (ImageView) itemView.findViewById(R.id.imgService);
            tvNameService = (TextView) itemView.findViewById(R.id.tvNameService);
            tvCategories = (TextView) itemView.findViewById(R.id.tvCategories);
            tvUpdateService =  itemView.findViewById(R.id.tvUpdateService);
        }
    }
}
