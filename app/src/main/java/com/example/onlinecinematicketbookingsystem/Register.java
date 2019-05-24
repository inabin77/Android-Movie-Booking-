package com.example.onlinecinematicketbookingsystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    SharedPreferences prefs;

    private static final String TAG = Register.class.getSimpleName();
    EditText name, email, dob, phone, address,password;
    Button btnSignup;
    TextView textViewSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.regName);
        email = (EditText) findViewById(R.id.regEmail);
        dob = (EditText) findViewById(R.id.regDob);
        password = (EditText) findViewById(R.id.regPass);
        phone = (EditText) findViewById(R.id.regPhone);
        address = (EditText) findViewById(R.id.regAdd);
        textViewSignin = (TextView) findViewById(R.id.textViewSignIN);
        btnSignup = findViewById(R.id.btnSignUP);

        prefs = getSharedPreferences("setting_prefs", Context.MODE_PRIVATE);

        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(Register.this, MainActivity.class));
                startActivity(i);


            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick()");
                String namei = name.getText().toString();
                String addi = address.getText().toString();
                String emaili = email.getText().toString();
                String dobi = dob.getText().toString();
                String phonei = phone.getText().toString();
                String passi = password.getText().toString();

                save(namei, addi, dobi, emaili, phonei,passi);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
    public void save(String name, String address, String dob, String email, String phone, String password) {
        Log.d(TAG, "save");

        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("name", name);
        edit.putString("email", email);
        edit.putString("dob", dob);
        edit.putString("address", address);
        edit.putString("phone", phone);
        edit.putString("pass", password);
        edit.commit();

        String cachedEmail = prefs.getString("email", "~");
        String cachedPass = prefs.getString("password", "~");

        Log.d(TAG, cachedPass);
        Log.d(TAG, cachedEmail);


    }
}

