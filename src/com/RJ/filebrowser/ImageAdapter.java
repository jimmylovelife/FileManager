package com.RJ.filebrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private Integer[] viewicons = {
			R.drawable.ic_launcher
	};
	
	private Integer[] viewnames = {
			R.string.app_name
	};
	
	private class GridItem {
		ImageView icon;
		TextView  name;
	};
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GridItem item;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.image_item, null);
			item = new GridItem();
			item.icon = (ImageView) convertView.findViewById(R.id.imageicon);
			item.name = (TextView) convertView.findViewById(R.id.imagename);
			convertView.setTag(item);
		} else {
			item = (GridItem) convertView.getTag();
		}
		item.icon.setImageResource(viewicons[0]);
		item.name.setText(viewnames[0]);
		return convertView;
	}
	
	public ImageAdapter(Context context) {
		this.context = context;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

}
