package com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinecinematicketbookingsystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaHallFragment extends Fragment {


    public CinemaHallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cinema_hall, container, false);
    }

}
