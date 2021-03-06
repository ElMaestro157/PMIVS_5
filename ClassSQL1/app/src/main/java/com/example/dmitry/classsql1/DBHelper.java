package com.example.dmitry.classsql1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MainActivity.TABLE_NAME + " ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "record_time integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP ТАВLЕ IF EXISTS " + MainActivity.TABLE_NAME);
        onCreate(db);
    }
}
