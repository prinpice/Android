package com.android.payment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper {

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
    String month = monthFormat.format(date);
    String TABLE_NAME = "Payment"+month + "Fee";

    //생성자 - database 파일을 생성한다.
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //DB 처음 만들때 호출. - 테이블 생성 등의 초기 처리.
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE "+TABLE_NAME+" (name TEXT,  sgroup INTEGER, pay1 INTEGER, pay2 INTEGER, pay3 INTEGER, date TEXT, tempexcept INTEGER);");
        //result.append("\nFee 테이블 생성 완료.");
    }

    //DB 업그레이드 필요 시 호출. (version값에 따라 반응)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("my", TABLE_NAME);
//        db.execSQL("CREATE TABLE "+TABLEA_NAME+" (name TEXT,  sgroup INTEGER, pay1 INTEGER, pay2 INTEGER, pay3 INTEGER, date TEXT, tempexcept INTEGER);");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
