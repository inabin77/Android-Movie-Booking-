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
import com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments.ViewMovieFragment;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Models.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    List<Movie> movieList;
    Context context;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3001/";



    public MovieAdapter(List<Movie> itemModelList, Context context) {
        this.movieList = itemModelList;
        this.context = context;

    }

    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View movieView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_detail,
                viewGroup, false);

        return new MovieViewHolder(movieView);
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder movieViewHolder, int i) {
        final Movie movie = movieList.get(i);

        movieViewHolder.name.setText(movie.getName());
        movieViewHolder.desc.setText(movie.getDescription());
        movieViewHolder.price.setText(movie.getPrice());

        StrictMode();

        final String path = BASE_URL+"uploads/"+movieList.get(i).getPoster_link();
        System.out.println("Path: " +path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            movieViewHolder.image.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        movieViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewMovieFragment.class);
                intent.putExtra("id", movie.getTheatreID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name,desc, price;
        Button button;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.ivMovieImg);
            name = itemView.findViewById(R.id.tvName);
            desc = itemView.findViewById(R.id.tvDesc);
            price = itemView.findViewById(R.id.tvPrice);
            button = itemView.findViewById(R.id.btnSelectMovie);


        }


    }
}

