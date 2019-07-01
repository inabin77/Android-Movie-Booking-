package com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces;

import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Seats;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SeatInterface {
    @GET("api/v1/seats")
    Call<List<Seats>> getALLseats();
}
