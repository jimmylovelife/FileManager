package com.RJ.filebrowser;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.RJ.RJView.ImageAdapter;
import com.RJ.RJView.RJGridView;

public class MainActivity extends Activity {
	private RJGridView rjview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rjview = new RJGridView(this);
		setContentView(rjview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean ret = false;
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ret =  rjview.onBackPressed();
		}
		if (!ret)
			return super.onKeyDown(keyCode, event);
		return ret;
	}
}
