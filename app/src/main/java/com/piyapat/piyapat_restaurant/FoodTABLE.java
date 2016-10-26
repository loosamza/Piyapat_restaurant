package com.piyapat.piyapat_restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by scOnTz on 19/10/2559.
 */
public class FoodTABLE {
    private static final String FOOD_TABLE = "foodTABLE";
    private static final String COLUMN_ID_FOOD = "_id";
    private static final String COLUMN_FOOD = "Food";
    private static final String COLUMN_PRICE = "Price";
    private static final String COLUMN_SOURCE = "Source";


    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public FoodTABLE(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }//Constructor

    public long addNewFood(String strFood,
                           String strPrice,
                           String strSource) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FOOD, strFood);
        contentValues.put(COLUMN_PRICE, strPrice);
        contentValues.put(COLUMN_SOURCE, strSource);
        return writeSqLiteDatabase.insert(FOOD_TABLE, null, contentValues);
    }
}//Main Class
