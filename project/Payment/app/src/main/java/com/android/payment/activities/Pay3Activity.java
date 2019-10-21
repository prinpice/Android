package com.android.payment.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.payment.R;
import com.android.payment.databinding.ActivityPay3Binding;
import com.android.payment.models.MemberVO;
import com.android.payment.utils.DatabaseHelper;
import com.android.payment.views.adapters.Pay3Adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pay3Activity extends AppCompatActivity {

    ActivityPay3Binding pay3Binding;

//    TextView txt_pay3_total, txt_pay3_enrollment, txt_pay3_paid, txt_pay3_unpaid, txt_pay3_percent;

//    EditText et_pay3_name;

    DatabaseHelper databaseHelper;

    Pay3Adapter pay3Adapter;
//    ListView listview_pay3;


    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
    int dbVersion = Integer.parseInt(monthFormat.format(date));
    String TABLE_NAME = "Payment"+dbVersion+"Fee";

    final static String dbName = "fee.db";

    private int pay3_total_num, pay3_enrollment_num, pay3_paid_num, pay3_unpaid_num;
    private double pay3_percent_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pay3Binding = DataBindingUtil.setContentView(this, R.layout.activity_pay3);

//        txt_pay3_total = findViewById(R.id.txt_pay3_total);
//        txt_pay3_enrollment = findViewById(R.id.txt_pay3_enrollment);
//        txt_pay3_paid = findViewById(R.id.txt_pay3_paid);
//        txt_pay3_unpaid = findViewById(R.id.txt_pay3_unpaid);
//        txt_pay3_percent = findViewById(R.id.txt_pay3_percent);

//        et_pay3_name = findViewById(R.id.et_pay3_name);

        databaseHelper = new DatabaseHelper(this, dbName, null, dbVersion);

//        listview_pay3 = findViewById(R.id.listview_pay3);

        getPercent(0);

    }//onCreate()

    public void mOnClick(View v) {
        SQLiteDatabase db;
        String sql;
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
//        Spinner spinner_pay3_sgroup = findViewById(R.id.spinner_pay3_sgroup);
        String spinner_pay3_result = pay3Binding.spinnerPay3Sgroup.getSelectedItem().toString();


        switch (v.getId()) {

            case R.id.btn_pay3_save:
                String name = pay3Binding.etPay3Name.getText().toString();
                if (!getSearchName(name)){
                    Toast.makeText(getApplicationContext(), "해당 이름이 없습니다.", Toast.LENGTH_SHORT).show();
                }else {
//                    EditText et_pay3_pay = findViewById(R.id.et_pay3_pay);
                    db = databaseHelper.getWritableDatabase();

                    int pay3_pay = 0;
                    try{
                        pay3_pay = Integer.parseInt(pay3Binding.etPay3Pay.getText().toString().trim());
                    }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(), "숫자만 입력해주세요", Toast.LENGTH_SHORT).show();
                        break;
                    }



                    sql = String.format("UPDATE "+TABLE_NAME+" SET pay3 = " + pay3_pay + " WHERE name = '" + name + "';");
                    db.execSQL(sql);
                    getPercent(0);
                    if (spinner_pay3_result.equals("선택")){
                        spinner_pay3_result = "전체";
                    }
                    getSearchPay3(pay3_pay, spinner_pay3_result);
                    Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                    pay3Binding.etPay3Name.setText(null);
                    pay3Binding.etPay3Pay.setText(null);




                }
                break;

            case R.id.btn_pay3_paid:
                if (spinner_pay3_result.equals("선택")) {
                    Toast.makeText(getApplicationContext(), "group을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    getSearchPay3(1, spinner_pay3_result);
                }
                break;

            case R.id.btn_pay3_unpaid:
                if (spinner_pay3_result.equals("선택")) {
                    Toast.makeText(getApplicationContext(), "group을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    getSearchPay3(0, spinner_pay3_result);
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
            sql = "SELECT * FROM " + TABLE_NAME + "";
        }else{
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE sgroup = " + sgroup;
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE sgroup = " + sgroup;
        }
        Cursor cursor = db.rawQuery(sql, null);
        pay3_total_num = cursor.getCount();
        pay3Binding.txtPay3Total.setText("총원 : "+ pay3_total_num + "명");
        if (sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE tempexcept = 0";
        }else{
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE tempexcept = 0 AND sgroup =" + sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay3_enrollment_num = cursor.getCount();
        pay3Binding.txtPay3Enrollment.setText("재적: " + pay3_enrollment_num + "명");
        if (sgroup == 0) {
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE pay3 > 0 AND tempexcept = 0";
        }else{
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE pay3 > 0 AND tempexcept = 0 AND sgroup="+sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay3_paid_num = cursor.getCount();
        pay3Binding.txtPay3Paid.setText("납부 : "+ pay3_paid_num + "명");
        if(sgroup == 0){
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay3 = 0 AND tempexcept = 0";
        }else{
            sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay3 = 0 AND tempexcept = 0 AND sgroup = " + sgroup;
        }
        cursor = db.rawQuery(sql, null);
        pay3_unpaid_num = cursor.getCount();
        pay3Binding.txtPay3Unpaid.setText("미납 : "+ pay3_unpaid_num + "명");
        if (pay3_enrollment_num == 0){
            pay3_percent_num = 0;
        }else {
            pay3_percent_num = Math.round((pay3_paid_num * 1000 / pay3_enrollment_num) / 10.0);
        }
        pay3Binding.txtPay3Percent.setText("납부율 : " + pay3_percent_num + "%");

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

    public void getSearchPay3(int pay3, String spinner_pay3_result){

        pay3Adapter = new Pay3Adapter();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql;
        if (spinner_pay3_result.equals("전체")){
            if (pay3 == 0){
                sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay3 = 0 ORDER BY sgroup AND tempexcept;";
            }else {
                sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay3 > 0 ORDER BY sgroup AND tempexcept;";
            }
            getPercent(0);
        }else{
            int spinner_pay3_sgroup = Integer.parseInt(spinner_pay3_result);
            if (pay3 == 0){
                sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay3 = 0 AND sgroup = "+ spinner_pay3_sgroup+" ORDER BY name AND tempexcept;";
            }else {
                sql = "SELECT * FROM "+TABLE_NAME+" WHERE pay3 > 0 AND sgroup = "+ spinner_pay3_sgroup+" ORDER BY name AND tempexcept;";
            }
            getPercent(spinner_pay3_sgroup);

        }


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            pay3Binding.lvPay3.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()) {
                MemberVO memberVO = new MemberVO();
                memberVO.setName(cursor.getString(0));
                memberVO.setSgroup(cursor.getInt(1));
                memberVO.setPay3(cursor.getInt(4));
                memberVO.setTempexcept(cursor.getInt(6));

                pay3Adapter.addItem(memberVO);

            }

            pay3Binding.lvPay3.setAdapter(pay3Adapter);

        }else {
            pay3Binding.lvPay3.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "조회 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }// getSearchPay3()
}
