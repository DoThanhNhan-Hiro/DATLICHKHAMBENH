package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.CategoriesDTO;

import java.util.ArrayList;

public class CategoriesDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public CategoriesDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //thêm chuyên khoa
    public long insertRow(CategoriesDTO categoriesDTO){
        ContentValues val =new ContentValues();
        val.put(CategoriesDTO.colNameCategories,categoriesDTO.getName());

        long res = db.insert(CategoriesDTO.nameTable,null,val);
        return res;
    }
    //Cập nhật chuyên khoa
    public int udateRow(CategoriesDTO categoriesDTO){
        ContentValues val = new ContentValues();
        val.put(CategoriesDTO.colNameCategories,categoriesDTO.getName());
        String[] check = new String[]{categoriesDTO.getId()+""};

        int res = db.update(CategoriesDTO.nameTable,val,"id = ?",check);
        return res;
    }
    //Lấy da danh sách chuyên khoa
    public ArrayList<CategoriesDTO> selectAll(){
        ArrayList<CategoriesDTO> listDtoCategories = new ArrayList<>();
        Cursor cs = db.query(CategoriesDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                CategoriesDTO categoriesDTO = new CategoriesDTO();
                categoriesDTO.setId(cs.getInt(0));
                categoriesDTO.setName(cs.getString(1));

                listDtoCategories.add(categoriesDTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return listDtoCategories;
    }

    public CategoriesDTO getDtoCategories(int ma) {
        CategoriesDTO categoriesDTO = new CategoriesDTO();
        String where = "id = ?";
        String[] whereArgs = {ma + ""};
        Cursor cs = db.query("tbCategories", null, where, whereArgs, null, null, null);
        if (cs.moveToFirst()) {
            categoriesDTO.setId(cs.getInt(0));
            categoriesDTO.setName(cs.getString(1));
        }
        return categoriesDTO;
    }

}

