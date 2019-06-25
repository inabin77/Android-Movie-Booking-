package com.example.onlinecinematicketbookingsystem.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.example.onlinecinematicketbookingsystem.Interfaces.UserInterface;
import com.example.onlinecinematicketbookingsystem.Models.User;
import com.example.onlinecinematicketbookingsystem.R;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, email, password, password2;
    Button register1, login1;

    public String BASE_URL = "http://10.0.2.2:4000/";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.regfullname);
        email = findViewById(R.id.regemail);
        password = findViewById(R.id.regpassword);
        password2 = findViewById(R.id.regconfirmpassword);

        register1 = findViewById(R.id.btnregister);
        register1.setOnClickListener(this);

        login1 = findViewById(R.id.btnlogin);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);

            }
        });
    }

    @Override
    public void onClick(View view) {
        register();

    }

    private void register() {
        Log.d("VAL", "BTNCLICKED ");

        if (nullValidation()) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserInterface userInterface = retrofit.create(UserInterface.class);
            final User user = new User(name.getText().toString().trim(), email.getText().toString().trim(), password.getText().toString().trim(), password2.getText().toString().trim());
            Call<ResponseBody> call = userInterface.userSignup(user);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    Log.d("VAL", "success ");

                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "SIGNUP SUCCESSFUL", Toast.LENGTH_SHORT).show();

                        Log.d("VAL", "success response ");

                        name.setText("");
                        email.setText("");
                        password.setText("");
                        password2.setText("");
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else {
                        try{
                            Log.d("VAL", response.toString());

                            Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    Log.d("VAL", t.getLocalizedMessage());

                }
            });


        }
    }
    public boolean nullValidation(){
        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(email.getText().toString())){
            email.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(password2.getText().toString())){
            password2.setError("Required Field");
            return false;
        }

        return true;
    }


}

