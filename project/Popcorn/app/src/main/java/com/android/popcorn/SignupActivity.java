package com.android.popcorn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button btn_cancel, btn_signup;
    SQLiteDatabase mDatabase;

    TextInputEditText et_id, et_email, et_pwd, et_pwd_confirm;

//    MemberShipOpenHelper openHelper;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mDatabase = openOrCreateDatabase(
                Environment.getExternalStorageDirectory() + "/testDB/myuser.db",//읽어올 경로
                SQLiteDatabase.CREATE_IF_NECESSARY,//alt + enter => @SuppressLint("WrongConstant")추가
                null );

//        openHelper = new MemberShipOpenHelper(this);
//        mDatabase = openHelper.getWritableDatabase();
        btn_signup = findViewById(R.id.btn_signup);
        btn_cancel = findViewById(R.id.btn_cancel);
        et_id = findViewById(R.id.et_id);
        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);
        et_pwd_confirm = findViewById(R.id.et_pwd_confirm);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                String email = et_email.getText().toString();
                String pwd = et_pwd.getText().toString();
                String pwd_confirm = et_pwd_confirm.getText().toString();
                String sql = "SELECT * FROM user WHERE userID='"+id+"' OR email='"+email+"'";
                Cursor cursor = mDatabase.rawQuery(sql, null);
                if (cursor.getCount() == 1){
                    Toast.makeText(SignupActivity.this, "이미 존재하는 아이디 또는 이메일입니다.", Toast.LENGTH_SHORT).show();

                }else if(pwd.equals(pwd_confirm)){
                    String sql2 = "INSERT INTO user(userID, email, pwd) VALUES('"+id+"','"+email+"','"+pwd+"')";
                    mDatabase.execSQL(sql2);
                    Toast.makeText(SignupActivity.this, "회원가입이 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(SignupActivity.this, "비밀번호를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }//onCreate
}
