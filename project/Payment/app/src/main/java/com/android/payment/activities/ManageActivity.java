package com.android.payment.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.payment.R;
import com.android.payment.databinding.ActivityManageBinding;
import com.android.payment.models.MemberVO;
import com.android.payment.utils.DatabaseHelper;
import com.android.payment.views.adapters.ManageAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageActivity extends AppCompatActivity {

    ActivityManageBinding manageBinding;

    //    EditText nameEdit;
    DatabaseHelper databaseHelper;


    ManageAdapter adapter;
//    ListView listview_manage;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat monthFormat = new SimpleDateFormat("yyyyMM");
    int dbVersion = Integer.parseInt(monthFormat.format(date));
    String TABLE_NAME = "Payment"+dbVersion+"Fee";

    final static String dbName = "fee.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manageBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage);

//        nameEdit = findViewById(R.id.nameedit);




        databaseHelper = new DatabaseHelper(this, dbName, null, dbVersion);


//        listview_manage = findViewById(R.id.listview_manage);

    }//onCreate()

    public void mOnClick(View v) {
        SQLiteDatabase db;
        String sql;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        String name = manageBinding.etManageName.getText().toString().trim();
        Spinner spinner_manage_sgroup = findViewById(R.id.spinner_manage_sgroup);
        CheckBox checkbox_tempexcept = findViewById(R.id.checkbox_tempexcept);
        String spinner_result;
        int sgroup;
        int tempexcept;

        switch (v.getId()) {

            case R.id.btn_manage_insert: //추가 버튼(insert)

                if (name.length()<=0){

                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (getSearchData(name)) {
                    Toast.makeText(getApplicationContext(), "이미 존재하는 이름입니다.", Toast.LENGTH_SHORT).show();
                    break;
                }
                spinner_result = spinner_manage_sgroup.getSelectedItem().toString();
                try{
                    sgroup = Integer.parseInt(spinner_result);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "group을 선택해주세요", Toast.LENGTH_SHORT).show();
                    break;
                }
                tempexcept = 0;
                if (checkbox_tempexcept.isChecked()){
                    tempexcept = 1;
                }



                db = databaseHelper.getWritableDatabase();


                sql = String.format("INSERT INTO "+TABLE_NAME+" VALUES('" + name + "'," + sgroup + ", 0, 0, 0, '" + getTime + "',"+ tempexcept +");");
                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), "추가되었습니다", Toast.LENGTH_SHORT).show();
                manageBinding.etManageName.setText(null);
                checkbox_tempexcept.setChecked(false);

                break;

            case R.id.btn_manage_delete:
                if (!getSearchData(name)){
                    Toast.makeText(getApplicationContext(), "존재하지 않는 이름입니다.", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    sql = "DELETE FROM "+TABLE_NAME+" WHERE name = '"+name+"';";
                }
                db = databaseHelper.getWritableDatabase();
//                sql = "DELETE FROM "+TABLE_NAME+";";
                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), name+"님이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_manage_update:
                if (!getSearchData(name)) {
                    Toast.makeText(getApplicationContext(), "존재하지 않는 이름입니다.", Toast.LENGTH_SHORT).show();
                    break;
                }
                spinner_result = spinner_manage_sgroup.getSelectedItem().toString();
                try{
                    sgroup = Integer.parseInt(spinner_result);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "group을 선택해주세요", Toast.LENGTH_SHORT).show();
                    break;
                }
                tempexcept = 0;
                if (checkbox_tempexcept.isChecked()){
                    tempexcept = 1;
                }

                db = databaseHelper.getWritableDatabase();
                sql = String.format("UPDATE "+TABLE_NAME+" SET sgroup = " + sgroup + ", tempexcept = "+ tempexcept + " WHERE name = '" + name + "';");
                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
                manageBinding.etManageName.setText(null);
                checkbox_tempexcept.setChecked(false);
                getSearchData(name);
                break;
        }
        databaseHelper.close();
    }//

    // 추가 시 존재유무 확인
    public Boolean getSearchData(String name){

        adapter = new ManageAdapter();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE name = '"+name+"';";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                MemberVO mto = new MemberVO();
                mto.setName(cursor.getString(0));
                mto.setSgroup(cursor.getInt(1));

                adapter.addItem(mto);


//                txt_confirm.setText("");
//                txt_confirm.append(String.format("\n이름 = %s, group = %s",
//                        cursor.getString(0), cursor.getString(1)));
            }

            manageBinding.lvManage.setAdapter(adapter);


            return true;
        }else
            return false;
    }// getSearchData()
}
