package com.opencagedc.geosnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by demouser on 6/12/13.
 */
public class Score extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.score);

    }

    public void startGallery(View view){
        Intent intent = new Intent(Score.this, Gallery.class);
        startActivity(intent);
    }
    public void startMainActivity(View view){
        Intent intent = new Intent(Score.this, MainActivity.class);
        startActivity(intent);
    }


}

