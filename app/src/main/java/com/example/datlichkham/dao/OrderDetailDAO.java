package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.AllDTO;
import com.example.datlichkham.dto.OrderDetailDTO;

import java.util.ArrayList;

public class OrderDetailDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public OrderDetailDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
//Thêm chi tiết hóa đơn
    public long innsertRow(OrderDetailDTO orderDetailDTO) {
        ContentValues val = new ContentValues();
        val.put(OrderDetailDTO.colOrderId, orderDetailDTO.getOrder_id());
        val.put(OrderDetailDTO.colOrderDoctorId, orderDetailDTO.getOrderDoctor_id());

        long res = db.insert(OrderDetailDTO.nameTable, null, val);
        return res;
    }
    //Lấy danh sách chi tiết hóa đơn theo mã
    public OrderDetailDTO getOrderDetialDto(int ma){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        String where = "order_id = ?";
        String[] whereArgs = {ma+""};
        Cursor cs = db.query(OrderDetailDTO.nameTable,null,where,whereArgs,null,null,null,null);
        if(cs.moveToFirst()){
            orderDetailDTO.setOrder_id(cs.getInt(0));
            orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
        }
        return orderDetailDTO;
    }
//Lấy ra tất cả danh sách đặt lịch
    public ArrayList<OrderDetailDTO> selectAll() {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        Cursor cs = db.query(OrderDetailDTO.nameTable, null, null, null, null, null, null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));

                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<OrderDetailDTO> getListOrderDetailDtoByNoConfirm(int idUser) {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs = {idUser + ""};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbAccount.id = tbFile.user_id where tbOrders.status ='Chờ ngày khám' and  tbAccount.id = ? order by tbOrderDoctor.start_date";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<OrderDetailDTO> getListOrderToDay(String today) {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs = {today.trim()};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbAccount.id = tbFile.user_id where tbOrders.order_date = ? and tbOrders.status = 'Chờ ngày khám'";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

//Lấy ra danh sách đặt theo mã bệnh nhân
    public ArrayList<AllDTO> getListOrderByIdFile(int maBN){
        ArrayList<AllDTO> list = new ArrayList<>();
        String select ="select tbFile.fullname, tbOrderDoctor.doctor_id,tbOrderDoctor.start_date,tbOrderDoctor.start_time,tbOrderDetail.orderDoctor_id,tbOrderDetail.order_id  from tbOrders join tbFile on tbOrders.file_id=tbFile.id join tbOrderDetail on tbOrders.id=tbOrderDetail.order_id join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrderDoctor.file_id= "+maBN+" and tbOrders.status='Chờ ngày khám' ";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                AllDTO obj = new AllDTO();
                obj.setFullameUser(cursor.getString(0));
                obj.setIdDoctor(cursor.getInt(1));
                obj.setStartDate(cursor.getString(2));
                obj.setStartTime(cursor.getString(3));
                obj.setOrderDoctor_id(cursor.getInt(4));
                obj.setOrder_id(cursor.getInt(5));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

}
