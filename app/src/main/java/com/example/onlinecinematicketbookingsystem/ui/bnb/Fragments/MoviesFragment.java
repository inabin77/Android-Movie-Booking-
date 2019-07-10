package com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Adapter.MovieAdapter;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces.MovieInterface;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Movie;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.TestMovie;

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
public class MoviesFragment extends Fragment {

    List<Movie> movieList = new ArrayList<Movie>();
    RecyclerView recyclerView;
    Retrofit retrofit;

    public MoviesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        recyclerView = view.findViewById(R.id.recyclerMovies);
//
//        recyclerView.setAdapter(new MovieAdapter(movieList,getContext()));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadmovie();
        return view;

    }

    private void createInstance()
    {
        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3001/")
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    private void loadmovie()
    {
        createInstance();
        MovieInterface movieInterface = retrofit.create(MovieInterface.class);
        Call<TestMovie> listcall=movieInterface.getALLmovies();

        listcall.enqueue(new Callback<TestMovie>() {
            @Override
            public void onResponse(Call<TestMovie> call, Response<TestMovie> response) {
                //Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                List<Movie> list = response.body().getData();
                recyclerView.setAdapter(new MovieAdapter(list,getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<TestMovie> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
