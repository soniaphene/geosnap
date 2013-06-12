package com.opencagedc.geosnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by demouser on 6/12/13.
 */
public class Gallery extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.gallery);

        Intent galintent = new Intent(Gallery.this, PicListActivity.class);
        startActivity(galintent);
    }


    public void startGuess(View view){
        Intent intent = new Intent(Gallery.this, Guess.class);
        startActivity(intent);
    }




}
