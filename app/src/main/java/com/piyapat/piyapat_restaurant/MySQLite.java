package com.piyapat.piyapat_restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.piyapat.piyapat_restaurant.MySQLiteOpenHelper;

/**
 * Created by scOnTz on 26/10/2559.
 */

public class MySQLite {

    //Explicit

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    private static final String column_id = "_id";
    private static final String column_user = "User";
    private static final String column_password = "Password";
    private static final String column_name ="Name";

    public MySQLite(Context context){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
    }// Constructor

    public long addNewUser(String strUser,
                           String strPassword,
                           String strName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_user,strUser);
        contentValues.put(column_password,strPassword);
        contentValues.put(column_name,strName);
        return sqLiteDatabase.insert(user_table,null,contentValues);
    }
} //Main class
