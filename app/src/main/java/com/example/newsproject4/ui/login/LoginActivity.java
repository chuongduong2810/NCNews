package com.example.newsproject4.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsproject4.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText edt_username, edt_password;
    private TextView tv_forgot, tv_sign_up;
    private Button btn_login;
    private String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();

        //Xử lý Sign up
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MoveToSignUp();
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        //Lấy dữ liệu từ Sign up (nếu có)
        GetIntent();

        //Xử lý Login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetUpLogin();
            }
        });
    }


    private void Anhxa(){
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        tv_forgot = (TextView) findViewById(R.id.forgot_pass);
        tv_sign_up = (TextView) findViewById(R.id.sign_up);
        btn_login = (Button) findViewById(R.id.btn_sign_up);
    }


    //Xử lý Login
    private void SetUpLogin(){
        if (edt_username.getText().length() != 0 && edt_password.getText().length() != 0)
        {
            if (edt_username.getText().toString().equals(username) && edt_password.getText().toString().equals(password))
            {
                Toast.makeText(this, "Sign in Successfully !!!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(LoginActivity.this, "Please enter check your username and password", Toast.LENGTH_SHORT).show();
        }
    }

    //Lấy dữ liệu từ Sign up
    private void GetIntent(){
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        edt_username.setText(username);
        edt_password.setText(password);
    }
}
