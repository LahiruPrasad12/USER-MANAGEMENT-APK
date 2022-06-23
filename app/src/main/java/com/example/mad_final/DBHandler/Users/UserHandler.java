package com.example.mad_final.DBHandler.Users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mad_final.DATABASE.UserMaster;

import java.util.ArrayList;
import java.util.List;

public class UserHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public UserHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_QUERY =  "CREATE TABLE "+ UserMaster.TABLE_NAME + "("
                + UserMaster._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserMaster.COLUMN_NAME_USERNAME + " TEXT, "
                + UserMaster.COLUMN_NAME_PASSWORD + " TEXT)";
        db.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + UserMaster.TABLE_NAME;
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }


    //Register User
    public Long Register(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserMaster.COLUMN_NAME_USERNAME, username);
        values.put(UserMaster.COLUMN_NAME_PASSWORD, password);

        Long result = db.insert(UserMaster.TABLE_NAME,null,values);
        db.close();
        return  result;
    }


    //Login
    public Boolean login(String name, String password) {
        Boolean answer = false;
        System.out.println(name);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(UserMaster.TABLE_NAME, new String[]{UserMaster._ID, UserMaster.COLUMN_NAME_USERNAME, UserMaster.COLUMN_NAME_PASSWORD}, UserMaster.COLUMN_NAME_USERNAME + " =?",
                new String[]{String.valueOf(name)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToNext();

        System.out.println(cursor.getString(1));
        System.out.println(cursor.getString(2));
        System.out.println(cursor.getString(1).equals(name));
        if(cursor.getString(1).equals(name) && cursor.getString(2).equals(password)){
            answer = true;
        }
        return answer;
    }


    //Read All Information
    public List<Users> getInfo() {
        List<Users> ar = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = " SELECT * FROM " + UserMaster.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Users student = new Users();
                student.setUsername(cursor.getString(1));
                student.setPassword(cursor.getString(2));
                ar.add(student);
            } while (cursor.moveToNext());
        }
        return ar;
    }


    //get my profile
    public Users getSpecificUser(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(UserMaster.TABLE_NAME,new String[]{UserMaster._ID,UserMaster.COLUMN_NAME_USERNAME,UserMaster.COLUMN_NAME_PASSWORD},
                UserMaster.COLUMN_NAME_USERNAME + " =? ", new String[]{String.valueOf(name)},null,null,null,null);

        if(cursor != null)
            cursor.moveToNext();

        System.out.println(cursor.getString(2));
        Users user = new Users();
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        return user;
    }


    //delete account
    public void deleteUser(String name){
        SQLiteDatabase db = getReadableDatabase();
        String query = UserMaster.COLUMN_NAME_USERNAME+ " LIKE?";
        String slecetionArgs[] = {name};
        db.delete(UserMaster.TABLE_NAME,query,slecetionArgs);
    }

}
