package com.android.payment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Pay1Activity extends AppCompatActivity {

    TextView txt_pay1_total, txt_pay1_enrollment, txt_pay1_paid, txt_pay1_unpaid, txt_pay1_percent;

    EditText et_pay1_name;

    DatabaseHelper databaseHelper;

    Pay1Adapter pay1Adapter;
    ListView listview_pay1;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
    int dbVersion = Integer.parseInt(monthFormat.format(date));
    String TABLE_NAME = "Payment"+dbVersion+"Fee";

    final static String dbName = "fee.db";

    private int pay1_total_num, pay1_enrollment_num, pay1_paid_num, pay1_unpaid_num;
    private double pay1_percent_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay1);

        txt_pay1_total = findViewById(R.id.txt_pay1_total);
        txt_pay1_enrollment = findViewById(R.id.txt_pay1_enrollment);
        txt_pay1_paid = findViewById(R.id.txt_pay1_paid);
        txt_pay1_unpaid = findViewById(R.id.txt_pay1_unpaid);
        txt_pay1_percent = findViewById(R.id.txt_pay1_percent);

        et_pay1_name = findViewById(R.id.et_pay1_name);

        databaseHelper = new DatabaseHelper(this, dbName, null, dbVersion);

        listview_pay1 = findViewById(R.id.listview_pay1);

        getPercent(0);

    }//onCreate()

    public void mOnClick(View v) {
        SQLiteDatabase db;
        String sql;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        Spinner spinner_pay1_sgroup = findViewById(R.id.spinner_pay1_sgroup);
        String spinner_pay1_result = spinner_pay1_sgroup.getSelectedItem().toString();

        switch (v.getId()) {

            case R.id.btn_pay1_save:
                String name = et_pay1_name.getText().toString();
                if (!getSearchName(name)){
                    Toast.makeText(getApplicationContext(), "해당 이름이 없습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    CheckBox checkbox_pay1_paid = findViewById(R.id.checkbox_pay1_paid);
                    db = databaseHelper.getWritableDatabase();
                    int pay1_pay = 0;
                    if (checkbox_pay1_paid.isChecked()){
                        pay1_pay = 1;
                    }

                    sql = String.format("UPDATE "+TABLE_NAME+" SET pay1 = " + pay1_pay + " WHERE name = '" + name + "';");
                    db.execSQL(sql);
//                    getPercent(0);
                    if (spinner_pay1_result.equals("선택"))
                        spinner_pay1_result = "전체";
                    getSearchPay1(pay1_pay, spinner_pay1_result);
                    Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                    et_pay1_name.setText(null);
                    checkbox_pay1_paid.setChecked(false);



                }
                break;

            case R.id.btn_pay1_paid:
                if (spinner_pay1_result.equals("선택")) {
                    Toast.makeText(getApplicationContext(), "group을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    getSearchPay1(1, spinner_pay1_result);
                }



                break;

            case R.id.btn_pay1_unpaid:
                if (spinner_pay1_result.equals("선택")) {
                    Toast.makeText(getApplicationContext(), "group을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    getSearchPay1(0, spinner_pay1_result);
                }
                break;

        }
        databaseHelper.close();
    }

    //Percent
    public void getPercent(int sgroup){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql;
        if(sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME;
        }else{
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE sgroup = "+ sgroup;
        }
        Cursor cursor = db.rawQuery(sql, null);
        pay1_total_num = cursor.getCount();
        txt_pay1_total.setText("총원 : "+ pay1_total_num + "명");
        if (sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE tempexcept = 0";
        }else{
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE tempexcept = 0 AND sgroup = "+sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay1_enrollment_num = cursor.getCount();
        txt_pay1_enrollment.setText("재적 : " + pay1_enrollment_num + "명");
        if (sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE pay1 = 1 AND tempexcept = 0";
        }else{
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE pay1 = 1 AND tempexcept = 0 AND sgroup = "+sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay1_paid_num = cursor.getCount();
        txt_pay1_paid.setText("납부 : "+ pay1_paid_num + "명");
        if (sgroup == 0){
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay1 = 0 AND tempexcept = 0";
        }else{
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay1 = 0 AND tempexcept = 0 AND sgroup = "+sgroup ;
        }

        cursor = db.rawQuery(sql, null);
        pay1_unpaid_num = cursor.getCount();
        txt_pay1_unpaid.setText("미납 : "+ pay1_unpaid_num + "명");
        if (pay1_enrollment_num == 0){
            pay1_percent_num = 0;
        }else{
            pay1_percent_num = Math.round((pay1_paid_num *1000/ pay1_enrollment_num)/10.0);
        }

        txt_pay1_percent.setText("납부율 : " + pay1_percent_num + "%");

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

    public void getSearchPay1(int pay1, String spinner_pay1_result){

        pay1Adapter = new Pay1Adapter();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql;
        if (spinner_pay1_result.equals("전체")){
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay1 = "+pay1+" ORDER BY sgroup AND tempexcept;";
            getPercent(0);
        }else{
            int spinner_pay1_sgroup = Integer.parseInt(spinner_pay1_result);
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay1 = "+pay1+" AND sgroup = "+ spinner_pay1_sgroup+" ORDER BY name AND tempexcept;";
            getPercent(spinner_pay1_sgroup);
        }


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            listview_pay1.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()) {
                MemberVO memberVO = new MemberVO();
                memberVO.setName(cursor.getString(0));
                memberVO.setSgroup(cursor.getInt(1));
                memberVO.setPay1(cursor.getInt(2));
                memberVO.setTempexcept(cursor.getInt(6));

                pay1Adapter.addItem(memberVO);

            }

            listview_pay1.setAdapter(pay1Adapter);

        }else {
            listview_pay1.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "조회 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }// getSearchPay1()
}
