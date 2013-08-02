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
				for (File file : subs) {
					if (file.exists() && file.isDirectory()) {
						dealSubFiles(file);
					}
				}
			}
			break;
		}
		
	}
	@SuppressLint("NewApi")
	private void dealSubFiles(File subFile) {
		File [] subs = subFile.listFiles();
		if (subs == null) {
			return;
		}
		System.out.println("deal " + subFile.getAbsolutePath() + " have " + subs.length + " children");

		ImageAdapter adapter = files.get(subFile.getAbsolutePath());
		if (adapter != null) {
			adapter.clearCachedFiles();
			adapter.addToFiles(subs);
		} else {
			adapter = new ImageAdapter(caller.getContext());
			adapter.addToFiles(subs);
			files.put(subFile.getAbsolutePath(), adapter);
		}
		
		for (File sub : subs) {
			if (sub.exists() && sub.isDirectory()) 
				dealSubFiles(sub);
		}
	}

}
