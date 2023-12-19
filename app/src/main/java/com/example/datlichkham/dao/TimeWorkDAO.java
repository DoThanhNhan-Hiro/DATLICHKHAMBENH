package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.TimeWorkDTO;

import java.util.ArrayList;

public class TimeWorkDAO {
    SQLiteDatabase db;
    MyDbHelper myDbHelper;
    public TimeWorkDAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public void open(){
        db = myDbHelper.getWritableDatabase();
    }
    //Thêm thời gian làm việc
    public long insertRow(TimeWorkDTO obj){
        ContentValues values = new ContentValues();
        values.put("session", obj.getSession());
        return db.insert("tbTimeWork", null, values);

    }
    //Sửa thời gian làm việc
    public int updateRow(TimeWorkDTO obj ){
        ContentValues values = new ContentValues();
        values.put("session", obj.getSession());
        return db.update("tbTimeWork", values, "id=?", new String[]{obj.getId()+""});
    }
    //Xóa thòi gian làm việc
    public int deleteRow(TimeWorkDTO obj){
        return db.delete("tbTimeWork", "id=?", new String[]{obj.getId()+""});
    }
    //Lấy danh sách thời gian làm việc
    public ArrayList<TimeWorkDTO> getAll(){
        ArrayList<TimeWorkDTO> ds = new ArrayList<>();
        String select = "select * from tbTimeWork";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                TimeWorkDTO dt = new TimeWorkDTO();
                dt.setId(cursor.getInt(0));
                dt.setSession(cursor.getString(1));
                ds.add(dt);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return ds;
    }
    //Lấy thời gian làm việc theo mã
    public TimeWorkDTO getDtoTimeWork(int maThoiGian){
        TimeWorkDTO DTO =new TimeWorkDTO();
        String where = "id = ?";
        String[] whereArgs = {maThoiGian+""};
        Cursor cs = db.query(TimeWorkDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            DTO.setId(cs.getInt(0));
            DTO.setSession(cs.getString(1));
        }
        return DTO;
    }
}
