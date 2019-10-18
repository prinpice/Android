package com.android.popcorn;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.popcorn.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;

//    Button btn_signup, btn_login;
//    TextInputEditText TextInput_Id, TextInput_Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

//        TextInput_Id = findViewById(R.id.TextInput_Id);
//        TextInput_Password = findViewById(R.id.TextInput_Password);

        final Bundle bundle = new Bundle();
        bundle.putString("id", loginBinding.TextInputId.getText().toString());

//        btn_login = findViewById(R.id.btn_login);
        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtras(bundle);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

//        loginBinding.btnRegister = findViewById(R.id.btn_register);
        loginBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }//onCreate
}
