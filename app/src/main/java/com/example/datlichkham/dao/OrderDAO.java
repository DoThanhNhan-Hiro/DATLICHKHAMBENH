package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.OrderDTO;

import java.util.ArrayList;

public class OrderDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public OrderDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
//Thêm lịch khám
    public long insertRow(OrderDTO orderDTO){
        ContentValues val = new ContentValues();
        val.put(OrderDTO.colFile_id,orderDTO.getFile_id());
        val.put(OrderDTO.colOrder_time,orderDTO.getOrder_time());
        val.put(OrderDTO.colOrder_date,orderDTO.getOrder_date());
        val.put(OrderDTO.colOrder_status,orderDTO.getStatus());
        val.put(OrderDTO.colNote, orderDTO.getNote());
        long res = db.insert(OrderDTO.nameTable,null,val);
        return res;
    }
//Cập nhật lịch khám
    public int updateRow(OrderDTO orderDTO){
        ContentValues val = new ContentValues();
        val.put(OrderDTO.colFile_id,orderDTO.getFile_id());
        val.put(OrderDTO.colOrder_time,orderDTO.getOrder_time());
        val.put(OrderDTO.colOrder_date,orderDTO.getOrder_date());
        val.put(OrderDTO.colOrder_status,orderDTO.getStatus());
        val.put(OrderDTO.colNote, orderDTO.getNote());
        String[] check = new String[]{orderDTO.getId()+""};
        int res  =db.update(OrderDTO.nameTable,val,"id = ?",check);
        return res;
    }
    //Sắp xếp tử thấp đến cao theo mã
    public ArrayList<OrderDTO> selectDesc(){
        ArrayList<OrderDTO> list = new ArrayList<>();
        String orderBy = "id desc";
        Cursor cs = db.query(OrderDTO.nameTable,null,null,null,null,null,orderBy);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setId(cs.getInt(0));
                orderDTO.setFile_id(cs.getInt(1));
                orderDTO.setOrder_time(cs.getString(2));
                orderDTO.setOrder_date(cs.getString(3));
                orderDTO.setStatus(cs.getString(4));
                orderDTO.setNote(cs.getString(5));
                list.add(orderDTO);
                cs.moveToNext();
            }
        }
        return list;
    }
    //lấy ra danh sách đặt lịch từ mã đặt hàng
    public OrderDTO getOrderDTOById(int maDatLich){
        OrderDTO orderDTO = new OrderDTO();
        String where = "id = ?";
        String[] whereArgs = {maDatLich+""};
        Cursor cs = db.query(OrderDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            orderDTO.setId(cs.getInt(0));
            orderDTO.setFile_id(cs.getInt(1));
            orderDTO.setOrder_time(cs.getString(2));
            orderDTO.setOrder_date(cs.getString(3));
            orderDTO.setStatus(cs.getString(4));
            orderDTO.setNote(cs.getString(5));
        }
        return orderDTO;
    }

}
