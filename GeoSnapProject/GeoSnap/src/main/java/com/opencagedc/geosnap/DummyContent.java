package com.opencagedc.geosnap;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {

    public static class DummyItem {

        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return this.id;
        }
    }

    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    
    public static boolean initialized = false;
    public static void init(Context context) {
    	if (!initialized) {
    		new ListAssetsTask().execute(context);
    		initialized = true;
    	}
    }    
    
    public static class ListAssetsTask extends AsyncTask<Context, Void, String[]> {

    	Context mContext = null;
    	String[] mAssetFiles = null;

    	static final String IMG_DIRECTORY = "/storage/emulated/0/Pictures/Snappy";
        static final String ASSET_IMG_DIR = IMG_DIRECTORY;

    	@Override
    	protected String[] doInBackground(Context... params) {
    		mContext = params[0];
    		AssetManager assetManager = mContext.getAssets();

    		try {
    			mAssetFiles = assetManager.list(ASSET_IMG_DIR);


    		} catch (IOException e) {
    			Log.e("tag", e.getMessage());
    		}

            File file = new File(IMG_DIRECTORY);


    		for (String filename : file.list()) {
    			addItem(new DummyItem(filename, ASSET_IMG_DIR + 				java.io.File.separator + filename));
    		}
    		return mAssetFiles;
    	}
    } // ListAssetsTask    
}
