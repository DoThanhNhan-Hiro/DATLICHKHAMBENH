package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.OrderDoctorDTO;

import java.util.ArrayList;

public class OrderDoctorDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public OrderDoctorDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
//Thêm lịch đặt bác sĩ
    public long insertRow(OrderDoctorDTO orderDoctorDTO){
        ContentValues val = new ContentValues();
        val.put(OrderDoctorDTO.colFileId,orderDoctorDTO.getFile_id());
        val.put(OrderDoctorDTO.colDoctorId,orderDoctorDTO.getDoctor_id());
        val.put(OrderDoctorDTO.colStartTime,orderDoctorDTO.getStart_time());
        val.put(OrderDoctorDTO.colStartDate,orderDoctorDTO.getStart_date());
        val.put(OrderDoctorDTO.colTotal,orderDoctorDTO.getTotal());
        long res =db.insert(OrderDoctorDTO.nameTable,null,val);
        return res;
    }
    //Cập nhật bác sĩ theo lịch đặt
    public int updateRow(OrderDoctorDTO orderDoctorDTO){
        ContentValues val = new ContentValues();
        val.put(OrderDoctorDTO.colFileId,orderDoctorDTO.getFile_id());
        val.put(OrderDoctorDTO.colDoctorId,orderDoctorDTO.getDoctor_id());
        val.put(OrderDoctorDTO.colStartTime,orderDoctorDTO.getStart_time());
        val.put(OrderDoctorDTO.colStartDate,orderDoctorDTO.getStart_date());
        val.put(OrderDoctorDTO.colTotal,orderDoctorDTO.getTotal());
        int res = db.update(OrderDoctorDTO.nameTable,val,"id=?",new String[]{orderDoctorDTO.getId()+""});
        return res;
    }
//Xóa lịch đặt
    public int deleteRow(OrderDoctorDTO DTO){
        String[] check = new String[]{DTO.getId()+""};
        int res = db.delete(OrderDoctorDTO.nameTable,"id = ?",check);
        return res;
    }
    public ArrayList<OrderDoctorDTO> selectAll(){
        ArrayList<OrderDoctorDTO> list = new ArrayList<>();
        Cursor cs = db.query(OrderDoctorDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
                orderDoctorDTO.setId(cs.getInt(0));
                orderDoctorDTO.setFile_id(cs.getInt(1));
                orderDoctorDTO.setDoctor_id(cs.getInt(2));
                orderDoctorDTO.setStart_time(cs.getString(3));
                orderDoctorDTO.setStart_date(cs.getString(4));
                orderDoctorDTO.setTotal(cs.getFloat(5));

                list.add(orderDoctorDTO);
                cs.moveToNext();
            }
        }
        return list;
    }
    //Sắp xếp theo mã giảm dần
    public OrderDoctorDTO getOrderDoctorDtoDesc(){
        OrderDoctorDTO DTO = new OrderDoctorDTO();
        String orderBy = "id desc";
        Cursor cs = db.query(OrderDoctorDTO.nameTable,null,null,null,null,null,orderBy);
        if(cs.moveToFirst()){
            DTO.setId(cs.getInt(0));
            DTO.setFile_id(cs.getInt(1));
            DTO.setDoctor_id(cs.getInt(2));
            DTO.setStart_time(cs.getString(3));
            DTO.setStart_date(cs.getString(4));
            DTO.setTotal(cs.getFloat(5));
        }
        return DTO;
    }

    public OrderDoctorDTO getOrderDoctorDtoById(int maBS_LichDat){
        OrderDoctorDTO DTO = new OrderDoctorDTO();
        String where = "id = ?";
        String[] whereArgs = {maBS_LichDat+""};
        Cursor cs = db.query(OrderDoctorDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            DTO.setId(cs.getInt(0));
            DTO.setFile_id(cs.getInt(1));
            DTO.setDoctor_id(cs.getInt(2));
            DTO.setStart_time(cs.getString(3));
            DTO.setStart_date(cs.getString(4));
            DTO.setTotal(cs.getFloat(5));
        }
        return DTO;
    }

}
