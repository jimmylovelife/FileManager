package com.RJ.RJView;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import android.annotation.SuppressLint;
import android.os.Environment;

public class RJScanDIR implements Runnable {
	public ConcurrentHashMap<String, ImageAdapter> files;
	private RJGridView caller;
	public RJScanDIR (ConcurrentHashMap<String, ImageAdapter> files, RJGridView caller) {
		this.files = files;
		this.caller = caller;
	}
	@SuppressLint("NewApi")
	@Override
	public void run() {
		String root = RJGridView.rootPath;
		while(true) {
			if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			File rootfile = new File(root);
			if (rootfile.exists() && rootfile.isDirectory()) {
				File[] subs = rootfile.listFiles();
				ImageAdapter tmp = new ImageAdapter(this.caller.getContext());
				tmp.addToFiles(subs);
				files.put(root, tmp);
				caller.dataReady();
				break; //for test
			}
		}
		
	}

}
