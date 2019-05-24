package com.example.onlinecinematicketbookingsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText edt_username,edt_pass ;
    Button btn_log,btn_sign;

    SharedPreferences preferences;

    private static final String TAG = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edt_username = findViewById(R.id.edt_username);
        edt_pass = findViewById(R.id.edt_pass);
        btn_log = findViewById(R.id.btn_log);
        btn_sign = findViewById(R.id.btn_signup);

        preferences = getSharedPreferences("setting_prefs", Context.MODE_PRIVATE);


        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_username.getText().toString().isEmpty()) {
                    edt_username.setError("username cannot be empty");
                }

                if (edt_pass.getText().toString().isEmpty()) {
                    edt_pass.setError("Password cannot be empty");
                }

                String pname = preferences.getString("email", "");
                String ppass = preferences.getString("pass", "");

                if(edt_username.getText().toString().equals(pname) && edt_pass.getText().toString().equals(ppass)){
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(MainActivity.this, "Click Detected", Toast.LENGTH_LONG).show();


            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent signUpIntent = new Intent(MainActivity.this,Register.class);
                startActivity(signUpIntent);

            }
        });
    }
}