package com.example.anupamanurag.ticketbookingsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {
    public Databasehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(com.example.anupamanurag.ticketbookingsystem.DataBaseAdapter.DB_CREATE);

    }
    public void onUpgrade(SQLiteDatabase db, int _oldVersion, int _newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+"TEMPLATE");
        onCreate(db);
    }
}