package com.example.equakefinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Map extends AppCompatActivity  {
    // Ciara Scott S1709819
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //BottomNavigation

        // assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.Home);
        // perform Itemselctedlistner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext()
                                , com.example.equakefinal.MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Map:
                        startActivity(new Intent(getApplicationContext()
                                , Map.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Filter:
                        startActivity(new Intent(getApplicationContext()
                                , FilterActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }
}