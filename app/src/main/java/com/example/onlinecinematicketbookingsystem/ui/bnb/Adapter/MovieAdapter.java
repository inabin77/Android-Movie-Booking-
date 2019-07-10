package com.example.onlinecinematicketbookingsystem.ui.bnb.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments.CinemaFragment;
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

        View movieView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_movies,
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

        String poster = movie.getPoster_link();
        String name = poster.substring(poster.lastIndexOf("/") + 1);
        movieViewHolder.name.setText(movie.getName());
        movieViewHolder.desc.setText(movie.getDescription());
        movieViewHolder.price.setText(movie.getPrice());

        final String path = "http://10.0.2.2:3001/public/"+ name;
        System.out.println("Path: " +path);

        StrictMode();
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
                CinemaFragment fragment = new CinemaFragment();
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameall, fragment);
                fragmentTransaction.commit();
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

            image = itemView.findViewById(R.id.iv_movieimage);
            name = itemView.findViewById(R.id.tv_moviename);
            desc = itemView.findViewById(R.id.tv_moviedescription);
            price = itemView.findViewById(R.id.tv_movieprice);
            button = itemView.findViewById(R.id.viewCinemas);
        }
    }

}

