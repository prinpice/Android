package com.android.payment.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.payment.R;
import com.android.payment.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding menuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuBinding = DataBindingUtil.setContentView(this, R.layout.activity_menu);

    }//onCreate()

    public void mOnClick(View v) {

        switch (v.getId()) {

            case R.id.btn_menu_manage:
                Intent intent_manage = new Intent(MenuActivity.this, ManageActivity.class);
                startActivity(intent_manage);
                break;

            case R.id.btn_menu_pay1:
                Intent intent_pay1 = new Intent(MenuActivity.this, Pay1Activity.class);
                startActivity(intent_pay1);
                break;

            case R.id.btn_menu_pay2:
                Intent intent_pay2 = new Intent(MenuActivity.this, Pay2Activity.class);
                startActivity(intent_pay2);
                break;

            case R.id.btn_menu_pay3:
                Intent intent_pay3 = new Intent(MenuActivity.this, Pay3Activity.class);
                startActivity(intent_pay3);
                break;

        }
    }//mOnClick()
}
