package com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces;

import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.TestMovie;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MovieInterface {
    @GET("api/v1/movies")
    Call<TestMovie> getALLmovies();
}
