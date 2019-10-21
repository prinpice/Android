package com.android.payment.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.payment.R;
import com.android.payment.databinding.ActivityPay1Binding;
import com.android.payment.models.MemberVO;
import com.android.payment.utils.DatabaseHelper;
import com.android.payment.views.adapters.Pay1Adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pay1Activity extends AppCompatActivity {

    ActivityPay1Binding pay1Binding;

    DatabaseHelper databaseHelper;

    Pay1Adapter pay1Adapter;

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
        pay1Binding = DataBindingUtil.setContentView(this,  R.layout.activity_pay1);

        databaseHelper = new DatabaseHelper(this, dbName, null, dbVersion);

        getPercent(0);

    }//onCreate()

    public void mOnClick(View v) {
        SQLiteDatabase db;
        String sql;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        String spinner_pay1_result = pay1Binding.spinnerPay1Sgroup.getSelectedItem().toString();

        switch (v.getId()) {

            case R.id.btn_pay1_save:
                String name = pay1Binding.etPay1Name.getText().toString();
                if (!getSearchName(name)){
                    Toast.makeText(getApplicationContext(), "해당 이름이 없습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    db = databaseHelper.getWritableDatabase();
                    int pay1_pay = 0;
                    if (pay1Binding.checkboxPay1Paid.isChecked()){
                        pay1_pay = 1;
                    }

                    sql = String.format("UPDATE "+TABLE_NAME+" SET pay1 = " + pay1_pay + " WHERE name = '" + name + "';");
                    db.execSQL(sql);
//                    getPercent(0);
                    if (spinner_pay1_result.equals("선택"))
                        spinner_pay1_result = "전체";
                    getSearchPay1(pay1_pay, spinner_pay1_result);
                    Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                    pay1Binding.etPay1Name.setText(null);
                    pay1Binding.checkboxPay1Paid.setChecked(false);



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
        pay1Binding.txtPay1Total.setText("총원 : "+ pay1_total_num + "명");
        if (sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE tempexcept = 0";
        }else{
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE tempexcept = 0 AND sgroup = "+sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay1_enrollment_num = cursor.getCount();
        pay1Binding.txtPay1Enrollment.setText("재적 : " + pay1_enrollment_num + "명");
        if (sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE pay1 = 1 AND tempexcept = 0";
        }else{
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE pay1 = 1 AND tempexcept = 0 AND sgroup = "+sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay1_paid_num = cursor.getCount();
        pay1Binding.txtPay1Paid.setText("납부 : "+ pay1_paid_num + "명");
        if (sgroup == 0){
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay1 = 0 AND tempexcept = 0";
        }else{
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay1 = 0 AND tempexcept = 0 AND sgroup = "+sgroup ;
        }

        cursor = db.rawQuery(sql, null);
        pay1_unpaid_num = cursor.getCount();
        pay1Binding.txtPay1Unpaid.setText("미납 : "+ pay1_unpaid_num + "명");
        if (pay1_enrollment_num == 0){
            pay1_percent_num = 0;
        }else{
            pay1_percent_num = Math.round((pay1_paid_num *1000/ pay1_enrollment_num)/10.0);
        }

        pay1Binding.txtPay1Percent.setText("납부율 : " + pay1_percent_num + "%");

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
            pay1Binding.lvPay1.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()) {
                MemberVO memberVO = new MemberVO();
                memberVO.setName(cursor.getString(0));
                memberVO.setSgroup(cursor.getInt(1));
                memberVO.setPay1(cursor.getInt(2));
                memberVO.setTempexcept(cursor.getInt(6));

                pay1Adapter.addItem(memberVO);

            }

            pay1Binding.lvPay1.setAdapter(pay1Adapter);

        }else {
            pay1Binding.lvPay1.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "조회 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }// getSearchPay1()
}
