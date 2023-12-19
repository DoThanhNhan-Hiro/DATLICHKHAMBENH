package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.AllDTO;
import com.example.datlichkham.dto.DoctorDTO;

import java.util.ArrayList;

public class DoctorDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public DoctorDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
//thêm bác ssix
    public long insertRow(DoctorDTO doctorDTO) {
        ContentValues val = new ContentValues();
        val.put(DoctorDTO.colIdUser, doctorDTO.getUser_id());
        val.put(DoctorDTO.colBirthday, doctorDTO.getBirthday());
        val.put(DoctorDTO.colService_id, doctorDTO.getService_id());
        val.put(DoctorDTO.colRoom_id, doctorDTO.getRoom_id());
        val.put(DoctorDTO.colDescription, doctorDTO.getDescription());
        val.put(DoctorDTO.colTimework_id, doctorDTO.getTimework_id());

        long res = db.insert(DoctorDTO.nameTable, null, val);
        return res;
    }
//cập nhật bác sĩ
    public int updateRow(DoctorDTO doctorDTO) {
        ContentValues val = new ContentValues();
        val.put(DoctorDTO.colIdUser, doctorDTO.getUser_id());
        val.put(DoctorDTO.colBirthday, doctorDTO.getBirthday());
        val.put(DoctorDTO.colService_id, doctorDTO.getService_id());
        val.put(DoctorDTO.colRoom_id, doctorDTO.getRoom_id());
        val.put(DoctorDTO.colDescription, doctorDTO.getDescription());
        val.put(DoctorDTO.colTimework_id, doctorDTO.getTimework_id());

        String[] check = new String[]{doctorDTO.getId() + ""};

        int res = db.update(DoctorDTO.nameTable, val, "id = ?", check);
        return res;
    }
//Lấy da danh sách tất cả bác sĩ
    public ArrayList<DoctorDTO> selectAll() {
        ArrayList<DoctorDTO> listDtoDoctor = new ArrayList<>();
        Cursor cs = db.query(DoctorDTO.nameTable, null, null, null, null, null, null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                DoctorDTO doctorDTO = new DoctorDTO();
                doctorDTO.setId(cs.getInt(0));
                doctorDTO.setUser_id(cs.getInt(1));
                doctorDTO.setBirthday(cs.getString(2));
                doctorDTO.setService_id(cs.getInt(3));
                doctorDTO.setRoom_id(cs.getInt(4));
                doctorDTO.setDescription(cs.getString(5));
                doctorDTO.setTimework_id(cs.getInt(6));

                listDtoDoctor.add(doctorDTO);
                cs.moveToNext();
            }
        }
        return listDtoDoctor;
    }

//Lấy ra danh sách bác sĩ theo mã bác sĩ
    public DoctorDTO getDtoDoctorByIdDoctor(int idBacSi){
        DoctorDTO doctorDTO = new DoctorDTO();
        String where = "id = ?";
        String[] whereArgs = {idBacSi+""};
        Cursor cs = db.query(DoctorDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            doctorDTO.setId(cs.getInt(0));
            doctorDTO.setUser_id(cs.getInt(1));
            doctorDTO.setBirthday(cs.getString(2));
            doctorDTO.setService_id(cs.getInt(3));
            doctorDTO.setRoom_id(cs.getInt(4));
            doctorDTO.setDescription(cs.getString(5));
            doctorDTO.setTimework_id(cs.getInt(6));
        }
        return doctorDTO;
    }
    //Lấy ra danh sách bác sĩ theo chuyên khoa
    public ArrayList<DoctorDTO> getDocotrByIdService(int idService){
        ArrayList<DoctorDTO> list = new ArrayList<>();
        String[] whereArgs = {idService+""};
        String select = "select * from tbDoctor where service_id = ?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                DoctorDTO doctorDTO = new DoctorDTO();
                doctorDTO.setId(cs.getInt(0));
                doctorDTO.setUser_id(cs.getInt(1));
                doctorDTO.setBirthday(cs.getString(2));
                doctorDTO.setService_id(cs.getInt(3));
                doctorDTO.setRoom_id(cs.getInt(4));
                doctorDTO.setDescription(cs.getString(5));
                doctorDTO.setTimework_id(cs.getInt(6));

                list.add(doctorDTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return list;
    }
//Lấy ra danh sách bác sĩ theo thời gian làm việc
    public ArrayList<DoctorDTO> getListDoctorByTimeWord(String time, int idService, int idTimeWord, String dateNow){
        ArrayList<DoctorDTO> list = new ArrayList<>();
        String select ="select tbDoctor.*  from tbDoctor join tbTimeWork on tbDoctor.timework_id = tbTimeWork.id join tbTimeWorkDetail on tbTimeWork.id = tbTimeWorkDetail.timework_id \n" +
                " join tbServices on tbDoctor.service_id =  tbServices.id where tbDoctor.service_id= "+idService+" and tbDoctor.id\n" +
                " not in (select tbDoctor.id  from tbDoctor join tbTimeWork on tbDoctor.timework_id = tbTimeWork.id\n" +
                " join tbTimeWorkDetail on tbTimeWork.id = tbTimeWorkDetail.timework_id \n" +
                " join tbServices on tbDoctor.service_id =  tbServices.id join tbOrderDoctor on tbDoctor.id=tbOrderDoctor.doctor_id join tbOrderDetail on tbOrderDoctor.id = tbOrderDetail.orderDoctor_id join tbOrders on tbOrderDetail.order_id = tbOrders.id where tbOrderDoctor.start_time='"+time+"' and tbOrderDoctor.start_date='"+dateNow+"' and tbOrders.status='Chờ ngày khám') and tbTimeWorkDetail.time = '"+time+"' group by tbDoctor.id";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                DoctorDTO obj = new DoctorDTO();
                obj.setId(cursor.getInt(0));
                obj.setUser_id(cursor.getInt(1));
                obj.setBirthday(cursor.getString(2));
                obj.setService_id(cursor.getInt(3));
                obj.setRoom_id(cursor.getInt(4));
                obj.setDescription(cursor.getString(5));
                obj.setTimework_id(cursor.getInt(6));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    //Lấy ra danh scash bác sĩ theo mã loại khám
    public ArrayList<DoctorDTO> getListDoctorByIdService(int maService,int maThoiGIan,int idService1){
        ArrayList<DoctorDTO> list = new ArrayList<>();
        String[] whereArgs = new String[]{maService+"",maThoiGIan+"",maService+""};
        String selecdt = "select * from tbDoctor where service_id = ? and timework_id =3 or timework_id = ? and service_id = ?";
        Cursor cursor = db.rawQuery(selecdt,whereArgs);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                DoctorDTO obj = new DoctorDTO();
                obj.setId(cursor.getInt(0));
                obj.setUser_id(cursor.getInt(1));
                obj.setBirthday(cursor.getString(2));
                obj.setService_id(cursor.getInt(3));
                obj.setRoom_id(cursor.getInt(4));
                obj.setDescription(cursor.getString(5));
                obj.setTimework_id(cursor.getInt(6));
                list.add(obj);
                cursor.moveToNext();
            }
        }
        return list;
    }
    //Lấy ra danh sách bác sĩ theo lịch làm việc chi tiết
    public ArrayList<DoctorDTO> listDoctorbyIdTimeWorkDetail(String ngaybatdat,String ngayhomnay,int maDichVu){
        ArrayList<DoctorDTO> list = new ArrayList<>();
        String[] whereArgs= new String[]{ngaybatdat.trim(),ngayhomnay.trim(),maDichVu+""};
        String select = "select tbDoctor.id,tbDoctor.user_id,tbDoctor.birthday,tbDoctor.service_id,tbDoctor.room_id,tbDoctor.description,tbDoctor.timework_id from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id inner join tbServices on tbDoctor.service_id = tbServices.id where tbOrderDoctor.start_time = ?  and tbOrderDoctor.start_date = ? and tbServices.id = ?";
        Cursor cursor = db.rawQuery(select,whereArgs);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                DoctorDTO obj = new DoctorDTO();
                obj.setId(cursor.getInt(0));
                obj.setUser_id(cursor.getInt(1));
                obj.setBirthday(cursor.getString(2));
                obj.setService_id(cursor.getInt(3));
                obj.setRoom_id(cursor.getInt(4));
                obj.setDescription(cursor.getString(5));
                obj.setTimework_id(cursor.getInt(6));
                list.add(obj);
                cursor.moveToNext();
            }

        }
        return list;
    }
}
