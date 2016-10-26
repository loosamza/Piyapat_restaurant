package com.piyapat.piyapat_restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by scOnTz on 19/10/2559.
 */
public class OrderTABLE {
    private static final String ORDER_TABLE = "oderTABLE";
    private static final String COLUMN_ORDER_ID = "_id";
    private static final String COLUMN_OFFICER = "Officer";
    private static final String COLUMN_DESK = "Desk";
    private static final String COLUMN_FOOD = "Food";
    private static final String COLUMN_ITEM = "Item";


    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public OrderTABLE(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }//Constructor

    public long addNewOder(String strOfficer,
                           String strDesk,
                           String strFood,
                           String strItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_OFFICER, strOfficer);
        contentValues.put(COLUMN_DESK, strDesk);
        contentValues.put(COLUMN_FOOD, strFood);
        contentValues.put(COLUMN_ITEM, strItem);
        return writeSqLiteDatabase.insert(ORDER_TABLE, null, contentValues);
    }

}//Main class
