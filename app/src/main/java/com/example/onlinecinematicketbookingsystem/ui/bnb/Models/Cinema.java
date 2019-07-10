package com.example.onlinecinematicketbookingsystem.ui.bnb.Models;

public class Cinema {
    String _id;
    private String name;
    private String address;
    private String phone;
    private String poster_link;

    public Cinema(String _id, String name, String address, String phone, String poster_link) {
        this._id = _id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.poster_link = poster_link;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPoster_link() {
        return poster_link;
    }
}
