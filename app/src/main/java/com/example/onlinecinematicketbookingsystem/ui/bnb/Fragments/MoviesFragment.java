package com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Adapter.MovieAdapter;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Cinema;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    List<Movie> movieList = new ArrayList<Movie>();
    RecyclerView recyclerView;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<Cinema> cinemas = new ArrayList<Cinema>();
        List<String> shows = new ArrayList<String>();
        movieList.add(new Movie("","a","b","c","","","","","","",cinemas,shows));
        movieList.add(new Movie("","e","f","g","","","","","","",cinemas,shows));
        movieList.add(new Movie("","a","b","c","","","","","","",cinemas,shows));

        recyclerView = container.findViewById(R.id.recyclerMovies);

        recyclerView.setAdapter(new MovieAdapter(movieList,getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);

    }

}
