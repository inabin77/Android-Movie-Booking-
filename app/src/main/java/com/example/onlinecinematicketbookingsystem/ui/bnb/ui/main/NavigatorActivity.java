package com.example.onlinecinematicketbookingsystem.ui.bnb.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.example.onlinecinematicketbookingsystem.ui.bnb.MapsActivity;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.onlinecinematicketbookingsystem.R;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments.CinemaFragment;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments.MoviesFragment;
import com.example.onlinecinematicketbookingsystem.ui.bnb.Fragments.SeatFragment;

public class NavigatorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movies) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameall,new MoviesFragment())
                    .commit();

        } else if (id == R.id.nav_cinemas) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameall, new CinemaFragment())
                    .commit();

        } else if (id == R.id.nav_seats) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameall,new SeatFragment())
                    .commit();

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_setting) {
          //  Toast.makeText(this, "gds", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NavigatorActivity.this, MapsActivity.class);
           startActivity(intent);
           // finish();

        } else if (id == R.id.nav_logout) {
            Intent intent= new Intent(NavigatorActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
