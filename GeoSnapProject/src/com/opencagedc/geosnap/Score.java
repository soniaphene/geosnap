package com.opencagedc.geosnap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by demouser on 6/12/13.
 */
public class Score extends FragmentActivity {

    private Button button;
    private GoogleMap SGmap;
    private Marker guessMarker;
    private Marker actualMarker;
    private Location loc;
    Guess guesser = new Guess();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.score);
        FragmentManager fragMan = this.getSupportFragmentManager();
        SupportMapFragment frag = (SupportMapFragment) fragMan.findFragmentById(R.id.scoremap);
        SGmap =frag.getMap();

        guessMarker=SGmap.addMarker(new MarkerOptions()

                .position(Guess.marker.getPosition())
                .title("You Guessed")

        );
        guessMarker.showInfoWindow();

        actualMarker=SGmap.addMarker(new MarkerOptions()

                .position(Guess.picLatLng)
                .title("Actual")

        );
        actualMarker.showInfoWindow();
        Polyline line = SGmap.addPolyline(new PolylineOptions()
            .add(guessMarker.getPosition(),actualMarker.getPosition())
            .width(5)
            .color(Color.RED)
        );
        float dist = this.distFrom((float)guessMarker.getPosition().latitude,
                (float) guessMarker.getPosition().longitude,
                (float)actualMarker.getPosition().latitude,
                (float)actualMarker.getPosition().longitude);
        Log.i("Distance", "" + dist);

        TextView myOutput = (TextView) findViewById(R.id.distance);
        myOutput.setText("Distance: " + dist + " meters");


    }


    public void startMainActivity(View view){
        Intent intent = new Intent(Score.this, MainActivity.class);
        startActivity(intent);
    }

    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return new Float(dist * meterConversion).floatValue();
    }


}

