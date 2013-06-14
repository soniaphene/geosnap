package com.opencagedc.geosnap;

import android.app.Activity;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;

/**
 * Created by demouser on 6/12/13.
 */
public class Guess extends FragmentActivity {

    private Button button;
    public MapFragment frag;
    public GoogleMap Gmap;
    private final static String TAG = "SNAPPY";

    public LatLng mLatLng;
    public static Marker marker;
    public static LatLng picLatLng;
    public boolean isMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String path = intent.getStringExtra(PicDetailActivity.path);

        setContentView(R.layout.guess);


        EditText myOutput = (EditText) findViewById(R.id.pathlocation);
        myOutput.setText(path);
        isMarker=false;

        try{
            ExifInterface exifPic = new ExifInterface(path);
            float[] location = new float[2];
            boolean success = exifPic.getLatLong(location);

            picLatLng = new LatLng(location[0],location[1]);

            Log.i(TAG, path);

            Log.i(TAG, success+"");
            Log.i(TAG, Arrays.toString(location)+"");


        }catch(Exception e){
            e.printStackTrace();
        };


        FragmentManager fragMan = this.getSupportFragmentManager();
        SupportMapFragment frag = (SupportMapFragment) fragMan.findFragmentById(R.id.map);
        Gmap =frag.getMap();
        Gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Log.i("MARKER STATUS",""+(marker!=null));
                if(isMarker==false){
                Log.i("HEY", "InMapClick");
                /*if (marker.getPosition()!=null){
                    marker.remove();
                    Log.i("REMOVING","MARKER");
                }*/
                mLatLng=latLng;
                marker=Gmap.addMarker(new MarkerOptions()
                    .position(mLatLng)
                );
                isMarker=true;
                marker.setDraggable(true);
                Log.i("LOCATION",mLatLng.toString());

                }
            }
        });
    }


    public void startScore(View view){
        Intent intent = new Intent(Guess.this, Score.class);
        startActivity(intent);
    }

    public LatLng getGuessPos() {
        return marker.getPosition();
    }


}
