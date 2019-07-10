package com.example.onlinecinematicketbookingsystem.ui.bnb.Models;

import java.util.List;

public class Movie {
    private String theatreID;
    private String name;
    private String description;
    private String price;
    private String release_date;
    private String run_time;
    private String director;
    private String cast;
    private String trailer_link;
    private String poster_link;
    private List<Cinema> cinemas;
    private List<String> shows;

    public Movie(String theatreID, String name, String description, String price, String release_date, String run_time, String director, String cast, String trailer_link, String poster_link, List<Cinema> cinemas, List<String> shows) {
        this.theatreID = theatreID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.release_date = release_date;
        this.run_time = run_time;
        this.director = director;
        this.cast = cast;
        this.trailer_link = trailer_link;
        this.poster_link = poster_link;
        this.cinemas = cinemas;
        this.shows = shows;
    }

    public String getTheatreID() {
        return theatreID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getRun_time() {
        return run_time;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getTrailer_link() {
        return trailer_link;
    }

    public String getPoster_link() {
        return poster_link;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public List<String> getShows() {
        return shows;
    }
}

