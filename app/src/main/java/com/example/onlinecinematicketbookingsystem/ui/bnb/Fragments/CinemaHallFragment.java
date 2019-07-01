package com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces.CinemaInterface;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces.MovieInterface;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Cinema;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaHallFragment extends Fragment {

    List<Movie> movieList = new ArrayList<Movie>();
    RecyclerView recyclerView;
    Retrofit retrofit;

    public CinemaHallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        List<Cinema> cinemas = new ArrayList<Cinema>();
//        List<String> shows = new ArrayList<String>();
//        movieList.add(new Movie("","a","b","c","","","","","","",cinemas,shows));
//        movieList.add(new Movie("","e","f","g","","","","","","",cinemas,shows));
//        movieList.add(new Movie("","a","b","c","","","","","","",cinemas,shows));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cinema_hall, container, false);

//        recyclerView = view.findViewById(R.id.recyclerMovies);
//
//        recyclerView.setAdapter(new MovieAdapter(movieList,getContext()));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //loadmovie();
        return view;

    }

    private void createInstance()
    {
        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3001/")
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    private void loadcinema()
    {
        createInstance();
        CinemaInterface cinemaInterface = retrofit.create(CinemaInterface.class);
        Call<List<Cinema>> listcall=cinemaInterface.getALLcinemas();

        listcall.enqueue(new Callback<List<Cinema>>() {
            @Override
            public void onResponse(Call<List<Cinema>> call, Response<List<Cinema>> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cinema>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
