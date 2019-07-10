package com.example.onlinecinematicketbookingsystem.ui.bnb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments.MoviesFragment;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Cinema;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>{
    List<Cinema> cinemaList;
    Context context;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3001/";



    public CinemaAdapter(List<Cinema> itemModelList, Context context) {
        this.cinemaList = itemModelList;
        this.context = context;

    }

    public CinemaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View cinemaView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_cinemas,
                viewGroup, false);

        return new CinemaViewHolder(cinemaView);
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaAdapter.CinemaViewHolder cinemaViewHolder, int i) {
        final Cinema cinema = cinemaList.get(i);

//        cinemaViewHolder.name.setText(cinema.getName());
//        cinemaViewHolder.address.setText(cinema.getAddress());
//        cinemaViewHolder.phone.setText(cinema.getPhone());

        StrictMode();

        final String path = BASE_URL+"uploads/"+cinemaList.get(i).getPoster_link();
        System.out.println("Path: " +path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            cinemaViewHolder.image.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cinemaViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoviesFragment.class);
                //intent.putExtra("id", cinema.getTheatreID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cinemaList.size();
    }

    public class CinemaViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView Name,Address,Phone;
        Button button;


        public CinemaViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.ivCinemaImg);
            Name = itemView.findViewById(R.id.tvCinemaName);
            Address = itemView.findViewById(R.id.tvCinemaAddress);
            Phone = itemView.findViewById(R.id.tvCinemaPhone);
            //button = itemView.findViewById(R.id.bookseat);
        }
    }

}


