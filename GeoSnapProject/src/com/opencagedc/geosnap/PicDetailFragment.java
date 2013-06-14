package com.opencagedc.geosnap;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class PicDetailFragment extends Fragment {

	public static final String ARG_ITEM_ID = "item_id";
	public static final String LOG_TAG = "PicDetailFragment";

	DummyContent.DummyItem mItem;

	public PicDetailFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	public File getAlbumStorageDir(String albumName) {
		// Get the directory for the user's public pictures directory.
		File file = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				albumName);

		if (!file.mkdirs()) {
			Log.e(LOG_TAG, "Directory not created");
		}
		return file;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_pic_detail,
				container, false);
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.pic_detail))
					.setText(mItem.content);
			AssetManager am = getActivity().getAssets();
				//Drawable d = Drawable.createFromStream(am.open(mItem.content),
				//		null);
                Drawable d = Drawable.createFromPath(mItem.content);
				((ImageView) rootView.findViewById(R.id.pic_detail_pic))
						.setImageDrawable(d);

            System.out.println(mItem.content);
		}

		return rootView;
	}

    public String getLocationString() {
        return mItem.content;
    }

}
