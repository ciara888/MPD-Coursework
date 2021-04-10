package com.example.equakefinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment {

    GoogleMap mMap;
    ArrayList<Item> itemList;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {


        /*    mMap = googleMap;

            LatLng unitedKingdom = new LatLng(54, 2);
            for(int i = 0; i < itemList.size();i++)   {
                String Magnitude = itemList.get(i).getStrength().substring(11);
                BitmapDescriptor quakeMapMarkerIcon = null;
                if (Float.parseFloat(Magnitude) < 1) {
                    quakeMapMarkerIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                }
                else if (Float.parseFloat(Magnitude) >= 1 && Float.parseFloat(Magnitude) < 1.5)    {
                    quakeMapMarkerIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
                }
                else if (Float.parseFloat(Magnitude) >= 1.5)   {
                    quakeMapMarkerIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                }


               mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(itemList.get(i).getGeoLat()),
                       Double.parseDouble((itemList.get(i).getGeoLong())))).title(itemList.get(i).getLocation() +
                       "," + itemList.get(i).getStrength()).icon(quakeMapMarkerIcon));
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLng(unitedKingdom));*/


        }
    };


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_maps, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

          //  itemList = (ArrayList<Item>) getIntent().getExtras().getSerializable("data");
            super.onViewCreated(view, savedInstanceState);
            SupportMapFragment mapFragment =
                    (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(callback);
            }
        }

    };

