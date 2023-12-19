package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.RoomsDTO;

import java.util.ArrayList;

public class RoomsDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public RoomsDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Thêm phòng mới
    public long insertRow(RoomsDTO DTO){
        ContentValues val = new ContentValues();
        val.put(RoomsDTO.colName,DTO.getName());
        val.put(RoomsDTO.colLocaton,DTO.getLocation());
        long res = db.insert(RoomsDTO.nameTable,null,val);
        return res;
    }
    //Cập nhật lại phòng
    public int updateRow(RoomsDTO DTO){
        String[] check = new String[]{DTO.getId()+""};
        ContentValues val = new ContentValues();
        val.put(RoomsDTO.colName,DTO.getName());
        val.put(RoomsDTO.colLocaton,DTO.getLocation());

        int res = db.update(RoomsDTO.nameTable,val,"id = ?",check);
        return res;
    }
    //Lấy danh sách phòng
    public ArrayList<RoomsDTO> selectAll(){
        ArrayList<RoomsDTO> dsPhong = new ArrayList<>();
        Cursor cs = db.query(RoomsDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                RoomsDTO roomsDTO = new RoomsDTO();
                roomsDTO.setId(cs.getInt(0));
                roomsDTO.setName(cs.getString(1));
                roomsDTO.setLocation(cs.getString(2));

                dsPhong.add(roomsDTO);
                cs.moveToNext();
            }
        }
        return dsPhong;
    }
    public RoomsDTO getDtoRoomByIdRoom(int maPhong){
        RoomsDTO DTO = new RoomsDTO();
        String where = "id = ?";
        String[] whereArgs = {maPhong+""};
        Cursor cs = db.query(RoomsDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            DTO.setId(cs.getInt(0));
            DTO.setName(cs.getString(1));
            DTO.setLocation(cs.getString(2));
        }
        return DTO;
    }
}
