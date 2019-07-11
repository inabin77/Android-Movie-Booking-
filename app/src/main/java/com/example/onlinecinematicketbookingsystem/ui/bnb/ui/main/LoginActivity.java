package com.example.onlinecinematicketbookingsystem.ui.bnb.ui.main;

import android.app.Notification;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces.UserInterface;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.User;
import com.squareup.seismic.ShakeDetector;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.onlinecinematicketbookingsystem.ui.bnb.NotifyApp.CHANNEL_1_ID;

public class LoginActivity extends AppCompatActivity implements ShakeDetector.Listener {

    EditText email, password;
    Button login, register,vibrate;
    Vibrator vibrator;
    private NotificationManagerCompat notificationManager;

    public  String BASE_URL = "http://10.0.2.2:3001/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notificationManager =NotificationManagerCompat.from(this);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        ShakeDetector shakeDetector = new ShakeDetector(this);

        shakeDetector.start(sensorManager);


        email = findViewById(R.id.logemail);
        password = findViewById(R.id.logpassword);
        vibrate = findViewById(R.id.btnlog);
        login = findViewById(R.id.btnlog);
        register = (Button) findViewById(R.id.btnreg);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel1();
                vibrator =(Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vibrator.vibrate(500);
                    }
                });

                if (nullValidation()) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    UserInterface userInterface = retrofit.create(UserInterface.class);
                    final User user = new User();
                    user.setEmail(email.getText().toString().trim());
                    user.setPassword(password.getText().toString().trim());
                    Call<ResponseBody> call = userInterface.userLogin(user);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Log.d("VAL", "success ");

                            if(response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                                Log.d("VAL", "success response ");

                                email.setText("");
                                password.setText("");
                                startActivity(new Intent(LoginActivity.this, NavigatorActivity.class));

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
        });
    }

    public boolean nullValidation(){
        if (TextUtils.isEmpty(email.getText().toString())){
            email.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Required Field");
            return false;
        }
        return true;
    }

    @Override
    public void hearShake() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


    public void sendOnChannel1(){
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Users Login Successfully")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1,notification);
    }
}




