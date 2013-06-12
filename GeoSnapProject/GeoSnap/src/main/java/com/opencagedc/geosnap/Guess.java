package com.opencagedc.geosnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by demouser on 6/12/13.
 */
public class Guess extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.guess);

    }


    public void startScore(View view){
        Intent intent = new Intent(Guess.this, Score.class);
        startActivity(intent);
    }


}
