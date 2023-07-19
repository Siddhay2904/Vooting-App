package com.example.vootingappjforce.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vootingappjforce.Adapters.DBHelper;
import com.example.vootingappjforce.R;

public class LoginPageActivity extends AppCompatActivity {

    EditText UserName, Password;
    Button LoginBtn, RegisterBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        UserName = findViewById(R.id.user);
        Password = findViewById(R.id.pass);
        LoginBtn = findViewById(R.id.LoginBtn);
        RegisterBtn = findViewById(R.id.RegisterBtn);
        DB = new DBHelper(this);

        DB.addCandidate("Candidate_1",0);
        DB.addCandidate("Candidate_2", 0);
        DB.addCandidate("Candidate_3", 0);
        DB.addCandidate("Candidate_4", 0);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = UserName.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if(username.equals("")||pass.equals(""))
                    Toast.makeText(LoginPageActivity.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean ChkUserPass = DB.checkUserPass(username,pass);
                    if(ChkUserPass==true){
                        Intent intent = new Intent(getApplicationContext(), VotingPageActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginPageActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RegistrationPageActivity.class);
                startActivity(intent);

            }
        });

    }



}