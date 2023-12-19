package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.TimeWorkDTO;
import com.example.datlichkham.dto.TimeWorkDetailDTO;

import java.util.ArrayList;

public class TimeWorkDetailDAO {
    SQLiteDatabase db;
    MyDbHelper dbhelper;

    public TimeWorkDetailDAO(Context context){
        dbhelper = new MyDbHelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    //Thêm chi tiết thời gian làm việc
    public long insertRow(TimeWorkDetailDTO dtoTimeWorkDetail){
        ContentValues values = new ContentValues();
        values.put(TimeWorkDetailDTO.colTime,dtoTimeWorkDetail.getTime());
        values.put(TimeWorkDetailDTO.colTimework_id,dtoTimeWorkDetail.getTimework_id());

        long res  =db.insert(TimeWorkDetailDTO.nameTable,null,values);
        return res;
    }
    //Xóa thời gian làm việc
    public int deleteRow(TimeWorkDetailDTO DTO){
        String[] check = new String[]{DTO.getId()+""};
        int res = db.delete(TimeWorkDetailDTO.nameTable,"id = ?",check);
        return res;
    }
    //Cập nhật thời gian làm việc chi tiết
    public int updateRow(TimeWorkDetailDTO DTO){
        ContentValues val = new ContentValues();
        val.put(TimeWorkDetailDTO.colTime,DTO.getTime());
        val.put(TimeWorkDetailDTO.colTimework_id,DTO.getTimework_id());
        String[] check = new String[]{DTO.getId()+""};

        int res = db.update(TimeWorkDetailDTO.nameTable,val,"id = ?",check);
        return res;
    }
    //Lấy ra tất cả thời gian làm việc chi tiết
    public ArrayList<TimeWorkDetailDTO> selectAll(){
        ArrayList<TimeWorkDetailDTO> ds = new ArrayList<>();
        Cursor cs = db.query(TimeWorkDetailDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDetailDTO dto = new TimeWorkDetailDTO();
                dto.setId(cs.getInt(0));
                dto.setTimework_id(cs.getInt(1));
                dto.setTime(cs.getString(2));

                ds.add(dto);
                cs.moveToNext();
            }
        }
        return ds;
    }

    public ArrayList<TimeWorkDetailDTO> selectTimeWorkDetailByTimeWorkId(int maThoiGian){
        ArrayList<TimeWorkDetailDTO> ds = new ArrayList<>();
        String where = "timework_id = ?";
        String[] whereArgs = new String[]{maThoiGian+""};
        Cursor cs = db.query(TimeWorkDetailDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDetailDTO DTO = new TimeWorkDetailDTO();
                DTO.setId(cs.getInt(0));
                DTO.setTimework_id(cs.getInt(1));
                DTO.setTime(cs.getString(2));

                ds.add(DTO);
                cs.moveToNext();
            }
        }
        return ds;
    }
    //Lấy ra danh sách làm việc chi tiết theo ngày
    public ArrayList<TimeWorkDetailDTO> listTimeWorkDetailByStartDate(String ngayBD , int maBacSi){
        ArrayList<TimeWorkDetailDTO> ds = new ArrayList<>();
        String[] check = {ngayBD.trim(),maBacSi+""};
        String select = "select tbOrderDoctor.start_time  from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id where tbOrderDoctor.start_date = ? and tbDoctor.id = ?";
        Cursor cs = db.rawQuery(select,check);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDetailDTO DTO = new TimeWorkDetailDTO();
                DTO.setTime(cs.getString(0));
                ds.add(DTO);
                cs.moveToNext();
            }
        }
        return ds;
    }
//Danh sách thời gian làm việc
   public ArrayList<TimeWorkDTO> listTimeWork(String gio){
        ArrayList<TimeWorkDTO> ds = new ArrayList<>();
        String[] whereArgs = {gio};
        String select = "select  tbTimeWork.id  from tbTimeWorkDetail inner join tbTimeWork on tbTimeWorkDetail.timework_id = tbTimeWork.id where time = ?";
        Cursor cs = db.rawQuery(select,whereArgs);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDTO DTO = new TimeWorkDTO();
                DTO.setId(cs.getInt(0));
                ds.add(DTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return ds;
   }
}
