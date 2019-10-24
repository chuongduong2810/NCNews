package com.example.newsproject4.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsproject4.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText edt_create_username, edt_create_accname, edt_create_pass, edt_confim_pass;
    private Button btn_create_acc;
    private String username, password,confim_pass, accname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        AnhXa();
        btn_create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckInput();
            }
        });
    }
    private void AnhXa(){
        edt_create_username = (EditText) findViewById(R.id.edt_create_username);
        edt_create_accname = (EditText) findViewById(R.id.edt_create_acc_name);
        edt_create_pass = (EditText) findViewById(R.id.edt_create_password);
        edt_confim_pass = (EditText) findViewById(R.id.edt_confirm_password);
        btn_create_acc = (Button) findViewById(R.id.btn_sign_up);
    }

    //Kiểm tra nhập thông tin
    private void CheckInput(){
        username = edt_create_username.getText().toString().trim();
        password = edt_create_pass.getText().toString().trim();
        confim_pass = edt_confim_pass.getText().toString().trim();
        accname = edt_create_accname.getText().toString();

        if(username.isEmpty() || password.isEmpty() || confim_pass.isEmpty() || accname.isEmpty())
        {
            Toast.makeText(SignUpActivity.this, "Please enter full information", Toast.LENGTH_SHORT).show();
        }
        else {
            if (password.equals(confim_pass) == false)
            {
                Toast.makeText(SignUpActivity.this, "Password is not same.\n Please check again !!!", Toast.LENGTH_SHORT).show();
            }
            else {
                Log.d("SignUp","check");
                Toast.makeText(SignUpActivity.this, "Successfully !!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

                intent.putExtra("username",username);
                intent.putExtra("password",password);

                startActivity(intent);
            }
        }
    }
}
