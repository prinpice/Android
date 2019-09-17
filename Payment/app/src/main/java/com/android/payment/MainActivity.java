package com.android.payment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    public void mOnClick(View v) {

        switch (v.getId()) {

            case R.id.btn_manage:
                Intent intent_manage = new Intent(MainActivity.this, ManageActivity.class);
                startActivity(intent_manage);
                break;

            case R.id.btn_pay1:
                Intent intent_pay1 = new Intent(MainActivity.this, Pay1Activity.class);
                startActivity(intent_pay1);
                break;

            case R.id.btn_pay2:
                Intent intent_pay2 = new Intent(MainActivity.this, Pay2Activity.class);
                startActivity(intent_pay2);
                break;

            case R.id.btn_pay3:
                Intent intent_pay3 = new Intent(MainActivity.this, Pay3Activity.class);
                startActivity(intent_pay3);
                break;

        }
    }//mOnClick()
}
