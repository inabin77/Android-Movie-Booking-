package com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Adapter.SeatAdapter;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Seats;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeatFragment extends Fragment {

    List<Seats> seatsList = new ArrayList<Seats>();
    List<Seats> seatsList1 = new ArrayList<Seats>();
    List<Seats> seatsList2 = new ArrayList<Seats>();
    RecyclerView recyclerView;
    Retrofit retrofit;

    public SeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seats, container, false);

        recyclerView = view.findViewById(R.id.recyclerSeats);

        recyclerView.setAdapter(new SeatAdapter(seatsList,getContext()));
        recyclerView.setAdapter(new SeatAdapter(seatsList1,getContext()));
        recyclerView.setAdapter(new SeatAdapter(seatsList2,getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),8));

        loadSeats();
        return view;

    }

    private void createInstance()
    {
        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3001/")
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    private void loadSeats()
    {
//        createInstance();
//        SeatInterface seatInterface = retrofit.create(SeatInterface.class);
//        Call<List<Seats>> listcall=seatInterface.getALLseats();
//
//        listcall.enqueue(new Callback<List<Seats>>() {
//            @Override
//            public void onResponse(Call<List<Seats>> call, Response<List<Seats>> response) {
//                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Seats>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
        seatsList.add(new Seats("A1"));
        seatsList.add(new Seats("A2"));
        seatsList.add(new Seats("A3"));
        seatsList.add(new Seats("A4"));
        seatsList.add(new Seats("B1"));
        seatsList.add(new Seats("B2"));
        seatsList.add(new Seats("B3"));
        seatsList.add(new Seats("B4"));
        seatsList1.add(new Seats("C1"));
        seatsList1.add(new Seats("C2"));
        seatsList1.add(new Seats("C3"));
        seatsList1.add(new Seats("C4"));
        seatsList2.add(new Seats("D1"));
        seatsList2.add(new Seats("D2"));
        seatsList2.add(new Seats("D3"));
        seatsList2.add(new Seats("D4"));
        seatsList2.add(new Seats("E1"));
        seatsList2.add(new Seats("E2"));
        seatsList2.add(new Seats("E3"));
        seatsList2.add(new Seats("E4"));
    }

}
