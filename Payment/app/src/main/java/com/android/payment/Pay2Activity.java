package com.android.payment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Pay2Activity extends AppCompatActivity {

    TextView txt_pay2_total, txt_pay2_enrollment, txt_pay2_paid, txt_pay2_unpaid, txt_pay2_percent;

    EditText et_pay2_name;

    DatabaseHelper databaseHelper;

    Pay2Adapter pay2Adapter;
    ListView listview_pay2;


    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
    int dbVersion = Integer.parseInt(monthFormat.format(date));
    String TABLE_NAME = "Payment"+dbVersion+"Fee";

    final static String dbName = "fee.db";

    private int pay2_total_num, pay2_enrollment_num, pay2_paid_num, pay2_unpaid_num;
    private double pay2_percent_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay2);

        txt_pay2_total = findViewById(R.id.txt_pay2_total);
        txt_pay2_enrollment = findViewById(R.id.txt_pay2_enrollment);
        txt_pay2_paid = findViewById(R.id.txt_pay2_paid);
        txt_pay2_unpaid = findViewById(R.id.txt_pay2_unpaid);
        txt_pay2_percent = findViewById(R.id.txt_pay2_percent);

        et_pay2_name = findViewById(R.id.et_pay2_name);

        databaseHelper = new DatabaseHelper(this, dbName, null, dbVersion);

        listview_pay2 = findViewById(R.id.listview_pay2);

        getPercent(0);
    }//onCreate()

    public void mOnClick(View v) {
        SQLiteDatabase db;
        String sql;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        Spinner spinner_pay2_sgroup = findViewById(R.id.spinner_pay2_sgroup);
        String spinner_pay2_result = spinner_pay2_sgroup.getSelectedItem().toString();


        switch (v.getId()) {

            case R.id.btn_pay2_save:
                String name = et_pay2_name.getText().toString();
                if (!getSearchName(name)){
                    Toast.makeText(getApplicationContext(), "해당 이름이 없습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    EditText et_pay2_pay = findViewById(R.id.et_pay2_pay);
                    db = databaseHelper.getWritableDatabase();

                    int pay2_pay = 0;
                    try{
                        pay2_pay = Integer.parseInt(et_pay2_pay.getText().toString().trim());
                    }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(), "숫자만 입력해주세요", Toast.LENGTH_SHORT).show();
                        break;
                    }



                    if (!getSearchPay2(name)){
                        Toast.makeText(getApplicationContext(), "이미 납부되었습니다", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    // 여기까지

                    sql = String.format("UPDATE "+TABLE_NAME+" SET pay2 = " + pay2_pay + " WHERE name = '" + name + "';");
                    db.execSQL(sql);
//                        getPercent(0);
                    if (spinner_pay2_result.equals("선택")){
                        spinner_pay2_result = "전체";
                    }
                    getSearchPay2(pay2_pay, spinner_pay2_result);
                    Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                    et_pay2_name.setText(null);
                    et_pay2_pay.setText(null);




                }
                break;

            case R.id.btn_pay2_paid:
                if (spinner_pay2_result.equals("선택")) {
                    Toast.makeText(getApplicationContext(), "group을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    getSearchPay2(1, spinner_pay2_result);
                }
                break;

            case R.id.btn_pay2_unpaid:
                if (spinner_pay2_result.equals("선택")) {
                    Toast.makeText(getApplicationContext(), "group을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    getSearchPay2(0, spinner_pay2_result);
                }
                break;

        }
//        }
        databaseHelper.close();
    }

    //Percent
    public void getPercent(int sgroup){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql;
        if (sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME;
        }else{
            sql = "SELECT * FROM "+TABLE_NAME + " WHERE sgroup = " + sgroup;
        }
        Cursor cursor = db.rawQuery(sql, null);
        pay2_total_num = cursor.getCount();
        txt_pay2_total.setText("총원 : "+ pay2_total_num + "명");
        if (sgroup == 0){
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE tempexcept = 0";
        }else {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE tempexcept = 0 AND sgroup = " + sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay2_enrollment_num = cursor.getCount();
        txt_pay2_enrollment.setText("재적 : " + pay2_enrollment_num + "명");
        if (sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE pay2 > 0 AND tempexcept = 0";
        }else{
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay2 > 0 AND tempexcept = 0 AND sgroup = " + sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay2_paid_num = cursor.getCount();
        txt_pay2_paid.setText("납부 : "+ pay2_paid_num + "명");
        if (sgroup == 0){
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay2 = 0 AND tempexcept = 0";
        }else {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE pay2 = 0 AND tempexcept = 0 AND sgroup = " + sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay2_unpaid_num = cursor.getCount();
        txt_pay2_unpaid.setText("미납 : "+ pay2_unpaid_num + "명");
        if (pay2_enrollment_num == 0){
            pay2_percent_num = 0;
        }else{
            pay2_percent_num = Math.round((pay2_paid_num *1000/ pay2_enrollment_num)/10.0);
        }
        txt_pay2_percent.setText("납부율 : " + pay2_percent_num + "%");

    }

    // 추가 시 존재유무 확인
    public Boolean getSearchName(String name){

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE name = '"+name+"';";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }// getSearchName()

    // TODO : 8/26 추가 코드
    // 저장시 납부 유무 검색
    public Boolean getSearchPay2(String name){

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name = '" + name + "' AND pay2 = 0;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }//getSearchPay2()
    // 여기까지


    // 납부/미납 검색
    public void getSearchPay2(int pay2, String spinner_pay2_result){

        pay2Adapter = new Pay2Adapter();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql;
        if (spinner_pay2_result.equals("전체")){
            if (pay2 == 0){
                sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay2 = 0 ORDER BY sgroup AND tempexcept;";
            }else {
                sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay2 > 0 ORDER BY sgroup AND tempexcept;";
            }
            getPercent(0);
        }else{
            int spinner_pay2_sgroup = Integer.parseInt(spinner_pay2_result);
            if (pay2 == 0){
                sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay2 = 0 AND sgroup = "+ spinner_pay2_sgroup+" ORDER BY name AND tempexcept;";
            }else {
                sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay2 > 0 AND sgroup = "+ spinner_pay2_sgroup+" ORDER BY name AND tempexcept;";
            }

            getPercent(spinner_pay2_sgroup);
        }


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            listview_pay2.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()) {
                MemberVO memberVO = new MemberVO();
                memberVO.setName(cursor.getString(0));
                memberVO.setSgroup(cursor.getInt(1));
                memberVO.setPay2(cursor.getInt(3));
                memberVO.setTempexcept(cursor.getInt(6));

                pay2Adapter.addItem(memberVO);

            }

            listview_pay2.setAdapter(pay2Adapter);

        }else {
            listview_pay2.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "조회 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }// getSearchPay2()
}
