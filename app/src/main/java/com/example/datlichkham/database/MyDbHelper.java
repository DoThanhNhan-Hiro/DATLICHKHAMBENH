package com.example.datlichkham.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "KhamBenh";
    public static final int DB_VERSION = 105;
    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlAccount = "CREATE TABLE  tbAccount  (id  INTEGER NOT NULL,userName  TEXT NOT NULL,passWord  TEXT NOT NULL,phone  TEXT NOT NULL,fullName  TEXT NOT NULL,role TEXT NOT NULL,img  TEXT ,PRIMARY KEY( ID  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlAccount);
        //Tài khoản ADMIN
        String sqlAdmin = "INSERT INTO tbAccount VALUES(1,'Admin','123','012313','My Admin','Admin',null)";
        sqLiteDatabase.execSQL(sqlAdmin);

        String sqlDoctor = "CREATE TABLE tbDoctor (id INTEGER NOT NULL,user_id INTEGER NOT NULL REFERENCES tbAccount(id),birthday TEXT NOT NULL, service_id INTEGER NOT NULL REFERENCES tbServices(id),room_id INTEGER NOT NULL REFERENCES tbRooms(id),description TEXT, timework_id INTEGER NOT NULL REFERENCES tbTimeWork(id),PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlDoctor);

        String sqlRooms = "CREATE TABLE tbRooms (id INTEGER NOT NULL,name TEXT NOT NULL,location TEXT,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlRooms);
        //innsert cho rooms
        String insertRooms1 = "INSERT INTO tbRooms VALUES(1,'P101','Tầng 1')";
        sqLiteDatabase.execSQL(insertRooms1);
        String insertRooms2 = "INSERT INTO tbRooms VALUES(2,'P102','Tầng 1')";
        sqLiteDatabase.execSQL(insertRooms2);
        String insertRooms3 = "INSERT INTO tbRooms VALUES(3,'P103','Tầng 1')";
        sqLiteDatabase.execSQL(insertRooms3);
        String insertRooms4 = "INSERT INTO tbRooms VALUES(4,'P104','Tầng 1')";
        sqLiteDatabase.execSQL(insertRooms4);

        String insertRooms5 = "INSERT INTO tbRooms VALUES(5,'P201','Tầng 2')";
        sqLiteDatabase.execSQL(insertRooms5);
        String insertRooms6 = "INSERT INTO tbRooms VALUES(6,'P302','Tầng 2')";
        sqLiteDatabase.execSQL(insertRooms6);
        String insertRooms7 = "INSERT INTO tbRooms VALUES(7,'P403','Tầng 2')";
        sqLiteDatabase.execSQL(insertRooms7);
        String insertRooms8 = "INSERT INTO tbRooms VALUES(8,'P504','Tầng 2')";
        sqLiteDatabase.execSQL(insertRooms8);

        String sqlServices = "CREATE TABLE tbServices (id INTEGER NOT NULL,name TEXT NOT NULL,price float NOT NULL,categories_id INTEGER NOT NULL REFERENCES tbCategories(id),img TEXT ,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlServices);

        String sqlCategories = "CREATE TABLE tbCategories (id INTEGER NOT NULL,name INTEGER NOT NULL,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlCategories);
        //innsert  categories
        String innertCategories1 = "INSERT INTO tbCategories VALUES(1,'Khám theo ngày')";
        sqLiteDatabase.execSQL(innertCategories1);
        String innertCategories2 = "INSERT INTO tbCategories VALUES(2,'Khám theo liệu trình')";
        sqLiteDatabase.execSQL(innertCategories2);

        String sqlFile = "CREATE TABLE tbFile (id INTEGER NOT NULL,fullname TEXT NOT NULL, user_id INTEGER REFERENCES tbAccount(id),birthday TEXT NOT NULL,cccd TEXT NOT NULL,country TEXT NOT NULL,bhyt TEXT NOT NULL,job TEXT NOT NULL,email TEXT NOT NULL,address TEXT NOT NULL,des TEXT, phoneNumber TEXT NOT NULL ,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlFile);

        String sqlTimeWork = "CREATE TABLE  tbTimeWork  (id  INTEGER NOT NULL,session  TEXT NOT NULL,PRIMARY KEY( id  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlTimeWork);

        //innert ca làm việc
        String insertTimeWork1 = "INSERT INTO tbTimeWork VALUES(1,'Buổi sáng')";
        sqLiteDatabase.execSQL(insertTimeWork1);
        String insertTimeWork2 = "INSERT INTO tbTimeWork VALUES(2,'Buổi chiều')";
        sqLiteDatabase.execSQL(insertTimeWork2);
        String insertTimeWork3 = "INSERT INTO tbTimeWork VALUES(3,'Cả ngày')";
        sqLiteDatabase.execSQL(insertTimeWork3);


        String sqlTimeWorkDetail = "CREATE TABLE  tbTimeWorkDetail  (id INTEGER NOT NULL,timework_id  INTEGER NOT NULL REFERENCES tbTimeWork(id),time TEXT NOT NULL,PRIMARY KEY( id  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlTimeWorkDetail);

        //innsert giờ làm theo ca
        String innsertTimeWorkDetail1 = "INSERT INTO tbTimeWorkDetail VALUES(1,1,'7:00-8:00')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail1);
        String innsertTimeWorkDetail2 = "INSERT INTO tbTimeWorkDetail VALUES(2,1,'8:15-9:15')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail2);
        String innsertTimeWorkDetail3 = "INSERT INTO tbTimeWorkDetail VALUES(3,1,'9:30-10:30')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail3);
        String innsertTimeWorkDetail4 = "INSERT INTO tbTimeWorkDetail VALUES(4,1,'10:45-11:45')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail4);

        String innsertTimeWorkDetail5 = "INSERT INTO tbTimeWorkDetail VALUES(5,2,'14:00-15:00')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail5);
        String innsertTimeWorkDetail6 = "INSERT INTO tbTimeWorkDetail VALUES(6,2,'15:15-16:15')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail6);
        String innsertTimeWorkDetail7 = "INSERT INTO tbTimeWorkDetail VALUES(7,2,'16:30-17:30')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail7);

        String innsertTimeWorkDetail8 = "INSERT INTO tbTimeWorkDetail VALUES(8,3,'7:00-8:00')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail8);
        String innsertTimeWorkDetail9 = "INSERT INTO tbTimeWorkDetail VALUES(9,3,'8:15-9:15')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail9);
        String innsertTimeWorkDetail10 = "INSERT INTO tbTimeWorkDetail VALUES(10,3,'9:30-10:30')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail10);
        String innsertTimeWorkDetail11 = "INSERT INTO tbTimeWorkDetail VALUES(11,3,'10:45-11:45')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail11);

        String innsertTimeWorkDetail12 = "INSERT INTO tbTimeWorkDetail VALUES(12,3,'14:00-15:00')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail12);
        String innsertTimeWorkDetail13 = "INSERT INTO tbTimeWorkDetail VALUES(13,3,'15:15-16:15')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail13);
        String innsertTimeWorkDetail14 = "INSERT INTO tbTimeWorkDetail VALUES(14,3,'16:30-17:30')";
        sqLiteDatabase.execSQL(innsertTimeWorkDetail14);

        String sqlOrders = "CREATE TABLE tbOrders (id INTEGER NOT NULL,file_id INTEGER NOT NULL REFERENCES tbFile(id),order_time TEXT NOT NULL,order_date TEXT NOT NULL,status TEXT,note TEXT,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlOrders);

        String sqlOrderDetail = "CREATE TABLE tbOrderDetail (order_id INTEGER NOT NULL REFERENCES tbOrders(id),orderDoctor_id INTEGER NOT NULL REFERENCES tbOrderDoctor(id));";
        sqLiteDatabase.execSQL(sqlOrderDetail);

        String sqlOrderDoctor = "CREATE TABLE tbOrderDoctor (id INTEGER NOT NULL,file_id INTEGER NOT NULL REFERENCES tbFile(id),doctor_id INTEGER NOT NULL REFERENCES tbDoctor(id),start_time TEXT NOT NULL,start_date TEXT NOT NULL,total FLOAT NOT NUll,PRIMARY KEY(id AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sqlOrderDoctor);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbAccount");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbDoctor");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbRooms");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbServices");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbCategories");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbTimeWork");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbTimeWorkDetail");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbOrders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbOrderDetail");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbFile");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbOrderDoctor");
        onCreate(sqLiteDatabase);
    }
}
