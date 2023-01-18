package com.example.clothdonationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public  static  final String DBNAME = "cloth_donation_app.db";
    public  static final String FNAME="fname";
    public  static final String LNAME="lname";
    public  static final String EMAIL="email";
    public  static final String PHONE="phone";
    public  static final String ADDRESS="address";
    public  static final String GENDER="gender";
    public  static final String CLOTHTYPE="cloth_type";
    public  static final String CLOTHSIZE="cloth_size";
    public DbHelper(Context context){
        super(context,DBNAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDb) {
        MyDb.execSQL("create table if not exists user(username TEXT primary key ,password TEXT , type TEXT)");
        MyDb.execSQL("create table if not exists donor(" +
                FNAME+" TEXT," +
                LNAME+" TEXT," +
                EMAIL+" TEXT," +
                PHONE+" TEXT ,"+
                ADDRESS+" TEXT," +
                GENDER+" TEXT," +
                CLOTHTYPE+" TEXT," +
                CLOTHSIZE+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDb, int i, int i1) {
        MyDb.execSQL("Drop table if exists user");
        MyDb.execSQL("Drop table if exists user");

    }

    public Boolean insertData(String username,String password,String type){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("type",type);
        long result  = MyDb.insert("user",null,contentValues);

        if(result == -1){
            return  false;
        }
        else{
            return  true;
        }
    }
    public Boolean insertDonorData(String sfname,String slname,String semail,String sphone,String saddress,String sgender,String sclothType,String sclothSize)
    {
        SQLiteDatabase Db=this.getWritableDatabase();
        DonorInfo donorInfo=new DonorInfo();
        ContentValues values=new ContentValues();
        values.put(FNAME,sfname);
        values.put(LNAME,slname);
        values.put(EMAIL,semail);
        values.put(PHONE,sphone);
        values.put(ADDRESS,saddress);
        values.put(GENDER,sgender);
        values.put(CLOTHTYPE,sclothType);
        values.put(CLOTHSIZE,sclothSize);
        long result=Db.insert("donor",null,values);
        if(result == -1){
            return  false;
        }
        else{
            return  true;
        }
    }
    public ArrayList<DonorInfo> fetchContact(){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM donor",null);
        ArrayList<DonorInfo> arrDonors=new ArrayList<>();
        while (cursor.moveToNext()){
            DonorInfo donr=new DonorInfo();
            donr.fname=cursor.getString(0);
            donr.lname=cursor.getString(1);
            donr.email=cursor.getString(2);
            donr.phone=cursor.getString(3);
            donr.address=cursor.getString(4);
            donr.gender=cursor.getString(5);
            donr.clothType=cursor.getString(6);
            donr.clothSize=cursor.getString(7);
            arrDonors.add(donr);
        }
        return arrDonors;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor =     MyDb.rawQuery("select * from user where username=?",new String[]{username});
        if(cursor.getCount()>0){
            return  true;
        }
        else{
            return  false;
        }
    }

    public Boolean checkUsernameAndPassword(String username,String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor =     MyDb.rawQuery("select * from user where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0){
            return  true;
        }
        else{
            return  false;
        }
    }
    public  String getTypeOfUser(String username,String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor =     MyDb.rawQuery("select * from user where username=? and password=?",new String[]{username,password});
        cursor.moveToNext();
        String type_of_user =  "";
        type_of_user = cursor.getString(2);
        return type_of_user;
    }
//    public  String getTypeOfUser(String username,String password){
//        SQLiteDatabase MyDb = this.getWritableDatabase();
//        Cursor cursor =     MyDb.rawQuery("select * from user where username=? and password=?",new String[]{username,password});
//        String type_of_user = cursor.getString(2);
//
//        return type_of_user;
//    }
}
