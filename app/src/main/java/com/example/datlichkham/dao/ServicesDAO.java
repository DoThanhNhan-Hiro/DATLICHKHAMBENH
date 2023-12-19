package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;

public class ServicesDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase sqLiteDatabase;

    public ServicesDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        sqLiteDatabase = myDbHelper.getWritableDatabase();
    }
//thên chuyên khoa
    public long insertServices(ServicesDTO DTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", DTO.getServicesName());
        contentValues.put("price", DTO.getServicesPrice());
        contentValues.put("categories_id", DTO.getCategoriesId());
        return sqLiteDatabase.insert("tbServices", null, contentValues);
    }
//Cập nhật chuyên khoa
    public int updateServices(ServicesDTO DTO) {
        ContentValues values = new ContentValues();
        values.put("name", DTO.getServicesName());
        values.put("price", DTO.getServicesPrice());
        values.put("categories_id", DTO.getCategoriesId());
        return sqLiteDatabase.update("tbServices", values, "id=?", new String[]{DTO.getServicesId() + ""});
    }
//Xóa chuyên khoa
    public int deleteServices(int servicesId) {
        return sqLiteDatabase.delete("tbServices", "id=?", new String[]{servicesId + ""});
    }
//Lấy ra danh sách khoa
    public ArrayList<ServicesDTO> selectAll() {
        ArrayList<ServicesDTO> ds = new ArrayList<>();
        Cursor cs = sqLiteDatabase.rawQuery("select * from tbServices", null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                ServicesDTO DTO = new ServicesDTO();
                DTO.setServicesId(cs.getInt(0));
                DTO.setServicesName(cs.getString(1));
                DTO.setServicesPrice(cs.getFloat(2));
                DTO.setCategoriesId(cs.getInt(3));

                ds.add(DTO);
                cs.moveToNext();
            }
        }
        return ds;
    }
    //Lấy ra danh sách chuyên khoan theo mã
    public ServicesDTO getDtoServiceByIdByService (int maCHuyenKhoa){
        ServicesDTO DTO = new ServicesDTO();
        String where = "id = ?";
        String[] whereArgs = {maCHuyenKhoa+""};
        Cursor cs = sqLiteDatabase.query(ServicesDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            DTO.setServicesId(cs.getInt(0));
            DTO.setServicesName(cs.getString(1));
            DTO.setServicesPrice(cs.getFloat(2));
            DTO.setCategoriesId(cs.getInt(3));
        }
        return DTO;
    }
    //tìm kiếm chuyên khoa theo tên chuyên khoa
    public ArrayList<ServicesDTO> getDtoServiceByIdByNameService (String tenChuyenKhoa){
        ArrayList<ServicesDTO> ds = new ArrayList<>();
        String where = "name like ?";
        String[] whereArgs = {"%"+tenChuyenKhoa.trim()+"%"};
        Cursor cs = sqLiteDatabase.query(ServicesDTO.nameTable,null,where,whereArgs,null,null,null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                ServicesDTO DTO = new ServicesDTO();
                DTO.setServicesId(cs.getInt(0));
                DTO.setServicesName(cs.getString(1));
                DTO.setServicesPrice(cs.getFloat(2));
                DTO.setCategoriesId(cs.getInt(3));

                ds.add(DTO);
                cs.moveToNext();
            }
        }
        return ds;
    }

}
