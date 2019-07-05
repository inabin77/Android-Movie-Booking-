package com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Interfaces.CinemaInterface;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Cinema;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.TestCinema;

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
public class CinemaFragment extends Fragment {

    List<Cinema> cinemaList = new ArrayList<Cinema>();
    RecyclerView recyclerView;
    Retrofit retrofit;

    public CinemaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cinemas, container, false);

        recyclerView = view.findViewById(R.id.recyclerCinemas);

        loadcinema();
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
        Call<TestCinema> listcall=cinemaInterface.getALLcinemas();

        listcall.enqueue(new Callback<TestCinema>() {
            @Override
            public void onResponse(Call<TestCinema> call, Response<TestCinema> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();

                List<Cinema> list = response.body().getData();
                Log.d("data",list.get(0).getName());
//                recyclerView.setAdapter(new CinemaAdapter(list,getContext()));
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }


            @Override
            public void onFailure(Call<TestCinema> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
