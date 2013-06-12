package com.opencagedc.geosnap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by demouser on 6/11/13.
 */
public class Gallery extends Activity {
    //In an Activity
    private String[] mFileList;
    private File mPath = new File(Environment.getExternalStorageDirectory() + "/storage/emulated/0/Pictures/Snappy/");
    private String mChosenFile;
    private static final String FTYPE = ".txt";
    private static final int DIALOG_LOAD_FILE = 1000;
    private final static String TAG = "GALLERY";

    private void loadFileList() {
        try {
            mPath.mkdirs();
        }
        catch(SecurityException e) {
            Log.e(TAG, "unable to write on the sd card " + e.toString());
        }
        if(mPath.exists()) {
            FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    File sel = new File(dir, filename);
                    return filename.contains(FTYPE) || sel.isDirectory();
                }
            };
            mFileList = mPath.list(filter);
        }
        else {
            mFileList= new String[0];
        }
    }

    protected Dialog onCreate(int id) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        setContentView(R.layout.gallery);

        switch(id) {
            case DIALOG_LOAD_FILE:
                builder.setTitle("Choose your file");
                if(mFileList == null) {
                    Log.e(TAG, "Showing file picker before loading the file list");
                    dialog = builder.create();
                    return dialog;
                }
                builder.setItems(mFileList, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mChosenFile = mFileList[which];
                        //you can do stuff with the file here too
                    }
                });
                break;
        }
        dialog = builder.show();
        return dialog;
    }
}
