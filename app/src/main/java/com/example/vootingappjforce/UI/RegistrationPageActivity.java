package com.example.vootingappjforce.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vootingappjforce.Adapters.DBHelper;
import com.example.vootingappjforce.R;

public class RegistrationPageActivity extends AppCompatActivity {

    EditText UserName, Password, EmailId, PhoneNum;
    Button LoginBtnRP, RegisterBtnRP;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        UserName = findViewById(R.id.username);
        Password = findViewById(R.id.pass);
        EmailId = findViewById(R.id.emailId);
        PhoneNum = findViewById(R.id.phoneNum);
        RegisterBtnRP = findViewById(R.id.registerBtn);
        LoginBtnRP = findViewById(R.id.LoginBtn);
        DB = new DBHelper(this);

        RegisterBtnRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = UserName.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String email = EmailId.getText().toString().trim();
                String phone = PhoneNum.getText().toString().trim();

                DB.addUser(username, password, email, phone);

                if(username.equals("")||password.equals("")||email.equals("")||phone.equals(""))
                    Toast.makeText(RegistrationPageActivity.this,"Please enter all the fields!", Toast.LENGTH_SHORT);
                else {
                    Boolean chechUser = DB.checkUser(username);
                    if(chechUser==false){
                        boolean insert = DB.addUser(username,password,email,phone);
                        if(insert==true){
                            Toast.makeText(RegistrationPageActivity.this, "Registration Successfully", Toast.LENGTH_SHORT);
                        }
                        else {
                            Toast.makeText(RegistrationPageActivity.this, "Registration Failed", Toast.LENGTH_SHORT);
                        }
                    }
                    else {
                        Toast.makeText(RegistrationPageActivity.this, "User Already Exist", Toast.LENGTH_SHORT);
                    }
                }

            }
        });

        LoginBtnRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginPageActivity.class);
                startActivity(intent);
            }
        });

    }
}