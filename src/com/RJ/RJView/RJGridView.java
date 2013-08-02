package com.RJ.RJView;

import java.util.concurrent.ConcurrentHashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;

import com.RJ.filebrowser.R;

@SuppressLint("NewApi")
public class RJGridView extends GridLayout {
	private Context context;
	GridView view;
	RJScanDIR rjjob;
	private String currentPath = null;
	public static String rootPath = Environment.getExternalStorageDirectory()
			.getAbsolutePath();

	public ConcurrentHashMap<String, ImageAdapter> files;

	private AdapterView.OnItemClickListener shortClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			RJGridCell cell = getClickedCell(position);
			if (cell != null)
				open(cell);
		}

	};

	public boolean onBackPressed() {
		ImageAdapter adapter = files.get(currentPath).getFather();
		if (adapter != null) {
			view.setAdapter(adapter);
			currentPath = adapter.getPath();
			return true;
		}
		return false;
	}

	protected void open(RJGridCell cell) {
		if (cell.isDir()) {
			currentPath = cell.getFile().getAbsolutePath();
			ImageAdapter adapter = files.get(currentPath);
			if (adapter != null) {
				view.setAdapter(adapter);
				
			} else {
				//TODO add parent path
				//currentPath = cell.getParent().getPath();
			}
		}
	}

	protected RJGridCell getClickedCell(int position) {
		if (currentPath == null)
			currentPath = rootPath;
		ImageAdapter adapter = files.get(currentPath);
		if (adapter != null)
			return (RJGridCell) adapter.getItem(position);
		return null;
	}

	public void dataReady() {
		if (currentPath == null)
			currentPath = rootPath;
		ImageAdapter adapter = files.get(currentPath);
		if (adapter != null)
			view.setAdapter(adapter);
	}
	
	public RJGridView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater flater = LayoutInflater.from(context);
		view = (GridView) flater.inflate(R.layout.rj_gridview, null);
		addView(view);
		view.setOnItemClickListener(shortClickListener);
		files = new ConcurrentHashMap<String, ImageAdapter>();
		rjjob = new RJScanDIR(files, this);
		rjjob.run();
	}
}
