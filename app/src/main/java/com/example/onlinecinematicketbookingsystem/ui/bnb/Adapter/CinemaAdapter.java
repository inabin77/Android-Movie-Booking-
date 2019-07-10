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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments.SeatFragment;
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

        View cinemaView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cinema,
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

        cinemaViewHolder.Name.setText(cinema.getName());
        cinemaViewHolder.Address.setText(cinema.getAddress());
        cinemaViewHolder.Phone.setText(cinema.getPhone());

        String[] shows = {"9:00 am","12:00 noon","3:00 pm","6:00 pm"};
        ArrayAdapter adapter=new ArrayAdapter<String>(context, R.layout.list_showtime,shows);
        cinemaViewHolder.Shows.setAdapter(adapter);

        String poster = cinema.getPoster_link();
        String name = poster.substring(poster.lastIndexOf("/") + 1);

        final String path = BASE_URL+"public/"+ name;
        System.out.println("Path: " +path);
        StrictMode();
        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            cinemaViewHolder.image.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cinemaViewHolder.Shows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                SeatFragment fragment = new SeatFragment();
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.frameall, fragment);
                fragmentTransaction.commit();
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
        ListView Shows;


        public CinemaViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.iv_Cinemaphoto);
            Name = itemView.findViewById(R.id.tv_Cinemaname);
            Address = itemView.findViewById(R.id.tv_CinemaAddress);
            Phone = itemView.findViewById(R.id.tv_Cinemaphone);
            Shows = itemView.findViewById(R.id.showslist);
        }
    }

}


