package com.example.onlinecinematicketbookingsystem.ui.bnb.Models;

import java.util.List;

public class Seats {
    private String movieID;
    private String cinemaID;
    private String show_time;
    private List<Seats> seats;

    public Seats(String movieID, String cinemaID, String show_time, List<Seats> seats) {
        this.movieID = movieID;
        this.cinemaID = cinemaID;
        this.show_time = show_time;
        this.seats = seats;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getCinemaID() {
        return cinemaID;
    }

    public String getShow_time() {
        return show_time;
    }

    public List<Seats> getSeats() {
        return seats;
    }
}
