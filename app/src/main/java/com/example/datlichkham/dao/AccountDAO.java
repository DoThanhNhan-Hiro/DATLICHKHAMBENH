package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.AccountDTO;

import java.util.ArrayList;

public class AccountDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    SharedPreferences sharedPreferences;

    public AccountDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sharedPreferences = context.getSharedPreferences("getIdUser", Context.MODE_PRIVATE);
    }
//Thêm tài khoản
    public long insertAccount(AccountDTO account) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", account.getUserName());
        contentValues.put("passWord", account.getPassWord());
        contentValues.put("fullName", account.getFullName());
        contentValues.put("phone", account.getPhoneNumber());
        contentValues.put("role", account.getRole());
        contentValues.put("img",account.getImg());
        return sqLiteDatabase.insert("tbAccount", null, contentValues);
    }
    //Cập nhật tài khoản
    public int updateAccount(AccountDTO account){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", account.getUserName());
        contentValues.put("passWord", account.getPassWord());
        contentValues.put("fullName", account.getFullName());
        contentValues.put("phone", account.getPhoneNumber());
        contentValues.put("role", account.getRole());
        contentValues.put("img",account.getImg());
        String[] check = new String[]{account.getId()+""};
        return sqLiteDatabase.update("tbAccount",contentValues,"id = ?",check);
    }

    public boolean checkLogin(String tenDN, String mk) {
        String where = "userName = ? and passWord = ?";
        String[] whereArgs = {tenDN.trim(), mk.trim()};
        Cursor cs = sqLiteDatabase.query("tbAccount", null, where, whereArgs, null, null, null);
        if (cs.getCount() > 0) {
            cs.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("idUser", cs.getInt(0));
            editor.putString("fullname", cs.getString(4));
            editor.putString("role", cs.getString(5));
            editor.putString("imgUser",cs.getString(6));

            editor.commit();

            return true;
        } else {
            return false;
        }
    }

    public AccountDTO getDtoAccount(int  idUser){
        AccountDTO dtoUser = new AccountDTO();
        String where = "id = ?";
        String[] whereArgs ={idUser+""};
        Cursor cs = sqLiteDatabase.query("tbAccount",null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            dtoUser.setId(cs.getInt(0));
            dtoUser.setUserName(cs.getString(1));
            dtoUser.setPassWord(cs.getString(2));
            dtoUser.setPhoneNumber(cs.getString(3));
            dtoUser.setFullName(cs.getString(4));
            dtoUser.setRole(cs.getString(5));
            dtoUser.setImg(cs.getString(6));

            cs.moveToNext();
        }
        cs.close();
        return dtoUser;
    }

    public ArrayList<AccountDTO> getAll(){
        ArrayList<AccountDTO> list = new ArrayList<>();
        String select ="select * from tbAccount";
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                AccountDTO obj = new AccountDTO();
                obj.setId(cursor.getInt(0));
                obj.setUserName(cursor.getString(1));
                obj.setPassWord(cursor.getString(2));
                obj.setPhoneNumber(cursor.getString(3));
                obj.setFullName(cursor.getString(4));
                obj.setRole(cursor.getString(5));
                obj.setImg(cursor.getString(6));
                list.add(obj);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public AccountDTO getTopDtoAccount(){
        AccountDTO accountDTO = new AccountDTO();
        String orderBy = "id desc";
        Cursor cs = sqLiteDatabase.query(AccountDTO.nameTable,null,null,null,null,null,orderBy);
        if(cs.moveToFirst()){
            accountDTO.setId(cs.getInt(0));
            accountDTO.setUserName(cs.getString(1));
            accountDTO.setPassWord(cs.getString(2));
            accountDTO.setPhoneNumber(cs.getString(3));
            accountDTO.setFullName(cs.getString(4));
            accountDTO.setRole(cs.getString(5));
            accountDTO.setImg(cs.getString(6));
        }
        return accountDTO;
    }
    public ArrayList<AccountDTO> getAccountDoctor(){
        ArrayList<AccountDTO> list = new ArrayList<>();
        String select ="select * from tbAccount where role ='Doctor'";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                AccountDTO obj = new AccountDTO();
                obj.setId(cursor.getInt(0));
                obj.setUserName(cursor.getString(1));
                obj.setPassWord(cursor.getString(2));
                obj.setPhoneNumber(cursor.getString(3));
                obj.setFullName(cursor.getString(4));
                obj.setRole(cursor.getString(5));
                obj.setImg(cursor.getString(6));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    public ArrayList<AccountDTO> getAccountUser(){
        ArrayList<AccountDTO> list = new ArrayList<>();
        String select ="select * from tbAccount where role ='User'";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                AccountDTO obj = new AccountDTO();
                obj.setId(cursor.getInt(0));
                obj.setUserName(cursor.getString(1));
                obj.setPassWord(cursor.getString(2));
                obj.setPhoneNumber(cursor.getString(3));
                obj.setFullName(cursor.getString(4));
                obj.setRole(cursor.getString(5));
                obj.setImg(cursor.getString(6));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

}
