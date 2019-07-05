package com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces;

import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.TestCinema;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CinemaInterface {
    @GET("api/v1/cinema")
    Call<TestCinema> getALLcinemas();
}
