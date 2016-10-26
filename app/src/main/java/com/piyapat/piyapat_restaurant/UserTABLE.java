package com.piyapat.piyapat_restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by scOnTz on 19/10/2559.
 */
public class UserTABLE {
    public static final String USER_TABLE = "userTABLE";
    public static final String CLUMN_ID = "_id";
    public static final String COLUMN_USER = "User";
    public static final String COLUMN_PASS = "Password";
    public static final String COLUMN_NAME = "Name";



    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public UserTABLE(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }//Constructor

    public long addNewUser(String strUser,
                           String strPassword,
                           String strName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER, strUser);
        contentValues.put(COLUMN_PASS, strPassword);
        contentValues.put(COLUMN_NAME, strName);
        return writeSqLiteDatabase.insert(USER_TABLE, null, contentValues);
    }
}//Main Class
