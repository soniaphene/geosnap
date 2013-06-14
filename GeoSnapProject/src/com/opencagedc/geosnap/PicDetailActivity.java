package com.opencagedc.geosnap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

public class PicDetailActivity extends FragmentActivity {

    public static final String ARG_ITEM_ID = "item_id";
    private PicDetailFragment fragment;
    public final static String path = "com.opencagedc.geosnap.PATH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(PicDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PicDetailFragment.ARG_ITEM_ID));
            fragment = new PicDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.pic_detail_container, fragment)
                    .commit();




        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, PicListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //start the Guess screen
    public void startGuess(View view){

        String location = fragment.getLocationString();
        System.out.println(location);

        Intent intent = new Intent(PicDetailActivity.this, Guess.class);

        intent.putExtra(path, location);


        startActivity(intent);
    }
}

