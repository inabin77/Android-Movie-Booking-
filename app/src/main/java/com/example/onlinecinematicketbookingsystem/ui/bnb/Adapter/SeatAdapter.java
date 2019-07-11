package com.example.onlinecinematicketbookingsystem.ui.bnb.Adapter;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Seats;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatsViewHolder>{
    List<Seats> seatsList;
    Context context;


    public SeatAdapter(List<Seats> itemModelList, Context context) {
        this.seatsList = itemModelList;
        this.context = context;

    }

    @NonNull
    @Override
    public SeatsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View seatsView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_seats,
                viewGroup, false);
        return new SeatsViewHolder(seatsView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SeatsViewHolder seatsViewHolder, int i) {
        final Seats seats = seatsList.get(i);
        seatsViewHolder.Name.setText(seats.getSeatName());

        seatsViewHolder.Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, seatsViewHolder.Name.getText() +"Seat Booked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return seatsList.size();
    }

//    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//        View seatsView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_seats,
//                viewGroup, false);
//
//        return new SeatViewHolder(seatsView);
//    }
//    private void StrictMode(){
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SeatAdapter.SeatsViewHolder seatsViewHolder, int i) {
//        final Seats seats = seatsList.get(i);
//
//        String[] Seats = {"A1","A2","A3","A4","A5","A6","A7","A8","A9"};
//        ArrayAdapter adapter=new ArrayAdapter<String>(context, R.layout.list_seats,Seats);
//        SeatsViewHolder.Seats.setAdapter(adapter);
//
//
//
//        SeatsViewHolder.Seats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String value = parent.getItemAtPosition(position).toString();
//                Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return seatsList.size();
//    }

    public class SeatsViewHolder extends RecyclerView.ViewHolder {

        public TextView Name;

        public SeatsViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_Seatsname);

        }
    }

}


