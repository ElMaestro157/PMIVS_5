package com.example.dmitry.classsql1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    EditText etName;
    DBHelper dbHelper;
    static final String TABLE_NAME = "CLASSMATES";
    long lastId = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);

        dbHelper = new DBHelper(getApplication());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);

        ContentValues cv = new ContentValues();

        cv.put("name", "Ivan");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        cv.put("name", "Jan");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        cv.put("name", "Roman");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        cv.put("name", "Alex");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);
        cv.put("name", "Vladimir");
        cv.put("record_time", System.currentTimeMillis());
        lastId = db.insert(TABLE_NAME, null, cv);

        dbHelper.close();
    }

    public void onClick(View v) {
        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnAdd:
                cv.put("name", name);
                cv.put("record_time", System.currentTimeMillis());
                lastId = db.insert(TABLE_NAME, null, cv);
                break;
            case R.id.btnRead:
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btnReplace:
                db.execSQL("update " + TABLE_NAME + " set name='Иванов Иван Иванович' "
                        + "where id = " + lastId);
                break;
        }
        dbHelper.close();
    }
}
