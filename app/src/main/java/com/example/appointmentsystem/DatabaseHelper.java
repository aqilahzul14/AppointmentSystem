package com.example.appointmentsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "appointment.db";

    SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + User.TABLE_NAME + " (ID integer PRIMARY KEY autoincrement, " +
                "  [FirstName] varchar(255), " +
                "  [LastName] varchar(255), " +
                "  [Email] varchar(255), " +
                "  [Password] varchar(255), " +
                "  [Gender] varchar(255))");
        db.execSQL("create table " + Appointment.TABLE_NAME + "(ID integer PRIMARY KEY autoincrement, " +
                "  [DateTime] varchar(255), " +
                "  [PatientName] varchar(255), " +
                "  [PhoneNo] varchar(255), " +
                "  [Email] varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);
    }


    public boolean saveUser(String fn, String ln, String email, String pass, String gender) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_2,fn);
        contentValues.put(User.COL_3,ln);
        contentValues.put(User.COL_4,email);
        contentValues.put(User.COL_5,pass);
        contentValues.put(User.COL_6,gender);
        long result = db.insert(User.TABLE_NAME,null ,contentValues);
        if(result == -1 )
            return false;
        else
            return true;
    }

    public boolean checkUser (String email, String pass){
        db = this.getWritableDatabase();
        String Query = "Select * from " + User.TABLE_NAME + " where " + User.COL_4 + " = " + email + " AND " + User.COL_5 + " = " + pass;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean saveAppointment(String dt, String pn, String email, String phone) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Appointment.COL_2,dt);
        contentValues.put(Appointment.COL_3,pn);
        contentValues.put(Appointment.COL_4,email);
        contentValues.put(Appointment.COL_5,phone);
        long result = db.insert(User.TABLE_NAME,null ,contentValues);
        if(result == -1 )
            return false;
        else
            return true;
    }

    class User {
        public static final String TABLE_NAME = "user_table";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "FirstName";
        public static final String COL_3 = "LastName";
        public static final String COL_4 = "Email";
        public static final String COL_5 = "Password";
        public static final String COL_6 = "Gender";
    }

    class Appointment {
        public static final String TABLE_NAME = "appointment_table";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "DateTime";
        public static final String COL_3 = "PatientName";
        public static final String COL_4 = "PhoneNo";
        public static final String COL_5 = "Email";
    }
}
