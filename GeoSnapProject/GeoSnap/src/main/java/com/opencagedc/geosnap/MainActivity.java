package com.opencagedc.geosnap;

import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends Activity {
    private final static String TAG = "SNAPPY";
    private final static int SNAP_IMAGE_REQUEST_CODE = 10;

    private Button button;
    private ImageView imageView;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(snapIt);
        imageView = (ImageView) findViewById(R.id.image_view);
    }

    OnClickListener snapIt = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = getOutputImageFileUri();

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, SNAP_IMAGE_REQUEST_CODE);

            //button.setText(R.string.share_it);


        }
    };

    private Uri getOutputImageFileUri() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Snappy");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e(TAG, "Couldn't create output directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        return Uri.fromFile(file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SNAP_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Log.i(TAG, "Picture saved to " + fileUri.getPath());

                System.out.println(fileUri.getPath());

                try{
                    ExifInterface exifPic = new ExifInterface(fileUri.getPath());
                    float[] location = new float[2];
                    boolean success = exifPic.getLatLong(location);

                    Log.i(TAG, success+"");
                    Log.i(TAG, Arrays.toString(location)+"");


                }catch(Exception e){
                    e.printStackTrace();
                };



            }
        }
    }

    public void startGallery(View view){
        Intent intent = new Intent(MainActivity.this, Gallery.class);
        startActivity(intent);
    }

    public void startHelp(View view){
        Intent intent = new Intent(MainActivity.this, Help.class);
        startActivity(intent);
    }
}
