package com.RJ.RJView;

import java.util.concurrent.ConcurrentHashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.widget.GridLayout;
import android.widget.GridView;

import com.RJ.filebrowser.R;

@SuppressLint("NewApi")
public class RJGridView extends GridLayout {
	private Context context;
	GridView view;
	RJScanDIR rjjob;
	private String currentPath = null;
	public static String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
	
	public ConcurrentHashMap<String, ImageAdapter> files; 
	public RJGridView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater flater = LayoutInflater.from(context);
		view = (GridView)flater.inflate(R.layout.rj_gridview, null);
		addView(view);
		files = new ConcurrentHashMap<String, ImageAdapter>();
		rjjob = new RJScanDIR(files, this);
		rjjob.run();
	}
	public void dataReady() {
		if (currentPath == null)
			currentPath = rootPath;
		ImageAdapter adapter = files.get(currentPath);
		if (adapter != null)
			view.setAdapter(adapter);
	}

}
