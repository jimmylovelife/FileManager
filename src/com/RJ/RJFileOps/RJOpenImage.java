package com.RJ.RJFileOps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class RJOpenImage extends RJOpenAction {
	
	public RJOpenImage(Context context, String path) {
		super(context, path);
	}
	@Override
	public void open() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(url), "image/*");
		//intent.setComponent(component)
		context.startActivity(intent);
	}

}
