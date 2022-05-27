package com.example.yourpocketstorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "YourPocketStorageDB";
    public static final String TABLE_ITEM = "item";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AMOUNT= "amount";
    public static final String KEY_PRICE = "price";
    public static final String KEY_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_ITEM + "(" + KEY_ID + " integer primary key," + KEY_NAME + " text,"
                + KEY_AMOUNT + " integer," + KEY_PRICE + " real," + KEY_DATE + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_ITEM);
        onCreate(sqLiteDatabase);

    }
}
