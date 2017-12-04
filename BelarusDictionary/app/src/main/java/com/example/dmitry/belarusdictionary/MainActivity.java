package com.example.dmitry.belarusdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;

    public static final String TABLE_NAME_REGIONS = "REGIONS";
    public static final String TABLE_NAME_DISTRICTS = "DISTRICTS";

    private ArrayList<CheckBox> checkBoxes;

    private RadioGroup radioButtons;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxes.add((CheckBox)findViewById(R.id.allCheckbox));
        checkBoxes.add((CheckBox)findViewById(R.id.brestskayaCheckbox));
        checkBoxes.add((CheckBox)findViewById(R.id.vitebskayaCheckbox));
        checkBoxes.add((CheckBox)findViewById(R.id.gomelskayaCheckbox));
        checkBoxes.add((CheckBox)findViewById(R.id.grodnenskayaCheckbox));
        checkBoxes.add((CheckBox)findViewById(R.id.minskayaCheckbox));
        checkBoxes.add((CheckBox)findViewById(R.id.mogilevskayaCheckbox));
        checkBoxes.add((CheckBox)findViewById(R.id.MinskCheckbox));

        radioButtons = findViewById(R.id.funcRadio);

        editText = findViewById(R.id.resultText);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME_DISTRICTS, null, null);
        db.delete(TABLE_NAME_REGIONS, null, null);

        ContentValues cv = new ContentValues();
        cv.put("district", "baranavicy");
        cv.put("population", 170000);
        cv.put("region", 1);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "biarosa");
        cv.put("population", 63700);
        cv.put("region", 1);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "ivacevicy");
        cv.put("population", 55000);
        cv.put("region", 1);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);

        cv.put("district", "braslau");
        cv.put("population", 26000);
        cv.put("region", 2);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "polack");
        cv.put("population", 108000);
        cv.put("region", 2);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "vicebsk");
        cv.put("population", 400000);
        cv.put("region", 2);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "vorsha");
        cv.put("population", 156000);
        cv.put("region", 2);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);

        cv.put("district", "homel");
        cv.put("population", 600000 );
        cv.put("region", 3);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "mazyr");
        cv.put("population", 132000);
        cv.put("region", 3);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "zlobin");
        cv.put("population", 102000);
        cv.put("region", 3);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);

        cv.put("district", "hrodna");
        cv.put("population", 420000);
        cv.put("region", 4);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "svislac");
        cv.put("population", 16000);
        cv.put("region", 4);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "slonim");
        cv.put("population", 65000);
        cv.put("region", 4);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);

        cv.put("district", "cerven");
        cv.put("population", 32000);
        cv.put("region", 5);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "salihorsk");
        cv.put("population", 134000);
        cv.put("region", 5);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "niasviz");
        cv.put("population", 39000);
        cv.put("region", 5);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);

        cv.put("district", "mahiliou");
        cv.put("population", 420000);
        cv.put("region", 6);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "asipovicy");
        cv.put("population", 48000);
        cv.put("region", 6);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "byhau");
        cv.put("population", 30000);
        cv.put("region", 6);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);

        cv.put("district", "savecki");
        cv.put("population", 161000);
        cv.put("region", 7);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "cantralny");
        cv.put("population", 108000);
        cv.put("region", 7);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);
        cv.put("district", "partyzanski");
        cv.put("population", 98000);
        cv.put("region", 7);
        db.insert(TABLE_NAME_DISTRICTS, null, cv);

        cv.put("name", "brestskaya");
        db.insert(TABLE_NAME_REGIONS, null, cv);

        cv.put("name", "vitebskaya");
        db.insert(TABLE_NAME_REGIONS, null, cv);

        cv.put("name", "homelskaya");
        db.insert(TABLE_NAME_REGIONS, null, cv);

        cv.put("name", "hrodnenskaya");
        db.insert(TABLE_NAME_REGIONS, null, cv);

        cv.put("name", "minskaya");
        db.insert(TABLE_NAME_REGIONS, null, cv);

        cv.put("name", "mahiliouskaya");
        db.insert(TABLE_NAME_REGIONS, null, cv);

        cv.put("name", "minsk");
        db.insert(TABLE_NAME_REGIONS, null, cv);

        dbHelper.close();
    }

    public void onClick(View v) {
        editText.setText("");

        ArrayList<Integer> regions = new ArrayList<>();
        for (int i = 1; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).isChecked()) {
                regions.add(i);
            }
        }
        boolean all = regions.isEmpty() || checkBoxes.get(0).isChecked() || regions.size() == 7;
        String where = getWhere(all, regions);

        db = dbHelper.getWritableDatabase();
        Cursor c;
        switch(radioButtons.getCheckedRadioButtonId()) {
            case R.id.sumRadio:
                String sql = "select sum(population) from " + TABLE_NAME_DISTRICTS + ((where != null) ? " where " + where : "") + ";";
                c = db.rawQuery(sql, null);
                int result = c.moveToFirst() ? c.getInt(0) : 0;
                c.close();
                editText.setText("Population sum is " + result);
                break;
            case R.id.minRadio:
                sql = "select min(population) from " + TABLE_NAME_DISTRICTS + (where != null ? " where " + where : "") + ";";
                c = db.rawQuery(sql, null);
                result = c.moveToFirst() ? c.getInt(0) : 0;
                c.close();
                editText.setText("Population min is " + result);
                break;
            case R.id.maxRadio:
                sql = "select max(population) from " + TABLE_NAME_DISTRICTS + (where != null ? " where " + where : "") + ";";
                c = db.rawQuery(sql, null);
                c.close();
                result = c.moveToFirst() ? c.getInt(0) : 0;
                editText.setText("Population max is " + result);
                break;
            case R.id.countRadio:
                editText.setText("Number of districst is " + DatabaseUtils.queryNumEntries(db, TABLE_NAME_DISTRICTS, where));
                break;
            case R.id.sortNameRadio:
                c = db.query(TABLE_NAME_DISTRICTS, null, where, null,  null, null, "name");
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionIndex = c.getColumnIndex("region");
                    do {
                        editText.setText(editText.getText().toString()
                                + "id = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region = " + c.getInt(regionIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    editText.setText("0 rows");
                c.close();
                break;
            case R.id.sortPopularityRadio:
                c = db.query(TABLE_NAME_DISTRICTS, null, where, null, null, null, "popularity");
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionIndex = c.getColumnIndex("region");
                    do {
                        editText.setText(editText.getText().toString()
                                + "id = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region = " + c.getInt(regionIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    editText.setText("0 rows");
                c.close();
                break;
            case R.id.sortRegionRadio:
                c = db.query(TABLE_NAME_DISTRICTS, null, where, null, null, null, "region");
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionIndex = c.getColumnIndex("region");
                    do {
                        editText.setText(editText.getText().toString()
                                + "id = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region = " + c.getInt(regionIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    editText.setText("0 rows");
                c.close();
                break;
            default:
                c = db.query(TABLE_NAME_DISTRICTS, null, where, null, null, null, null);
                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int populationColIndex = c.getColumnIndex("population");
                    int regionIndex = c.getColumnIndex("region");
                    do {
                        editText.setText(editText.getText().toString()
                                + "id = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", population = " + c.getInt(populationColIndex) +
                                ", region = " + c.getInt(regionIndex) + "\n");
                    } while (c.moveToNext());
                } else
                    editText.setText("0 rows");
                c.close();
                break;
        }

        radioButtons.clearCheck();
    }

    private String getWhere(boolean all, ArrayList<Integer> regions) {
        if (!all) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < regions.size() - 1; i++) {
                result.append("region=").append(regions.get(i)).append(" or ");
            }
            result.append("region=").append(regions.get(regions.size() - 1));
            return result.toString();
        }
        return null;
    }



    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "belarus", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // создаем таблицу с полями
            db.execSQL("create table " + TABLE_NAME_DISTRICTS + " ("
                    + "id integer primary key autoincrement,"
                    + "name text not null,"
                    + "population integer not null,"
                    + "region integer not null,"
                    + "foreign key(region) references regions(id)"+ ");");

            db.execSQL("create table " + TABLE_NAME_REGIONS + " ("
                    + "id integer primary key autoincrement,"
                    + "name text not null" + ");");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int
                newVersion) {
            db.execSQL( "drop table if exists " + TABLE_NAME_DISTRICTS + ";");
            db.execSQL( "drop table if exists " + TABLE_NAME_REGIONS + ";");
            onCreate( db);
        }
    }
}
