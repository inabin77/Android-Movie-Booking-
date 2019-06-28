package com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces;

import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface {
    @POST("api/v1/users/signup")
    Call<ResponseBody> userSignup(@Body User user);

    @POST("api/v1/auth/login")
    Call<ResponseBody> userLogin(@Body User user);


}
