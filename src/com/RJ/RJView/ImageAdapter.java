package com.RJ.RJView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.RJ.filebrowser.R;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<RJGridCell> files;
	
	private class GridItem {
		ImageView icon;
		TextView  name;
	};
	
	@Override
	public int getCount() {
		return files.size();
	}

	@Override
	public Object getItem(int position) {
		return files.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GridItem item;
		RJGridCell cell= files.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.image_item, null);
			item = new GridItem();
			item.icon = (ImageView) convertView.findViewById(R.id.imageicon);
			item.name = (TextView) convertView.findViewById(R.id.imagename);
			convertView.setTag(item);
		} else {
			item = (GridItem) convertView.getTag();
		}
		//item.icon.setImageBitmap(cell.getIcon());
		item.icon.setImageResource(R.drawable.ic_launcher);
		item.name.setText(cell.getName());
		return convertView;
	}
	
	public ImageAdapter(Context context) {
		this.context = context;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.files = new ArrayList<RJGridCell>();
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}
	
	public void addToFiles(File[] subs) {
		for (File file : subs) {
			RJGridCell cell = new RJGridCell(file);
			files.add(cell);
		}
		Collections.sort(files);
	}

	public void clearCachedFiles() {
		files.clear();
	}
}
