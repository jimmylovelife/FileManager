package com.RJ.RJFileOps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class RJOpenAPK extends RJOpenAction {
	public RJOpenAPK(Context context, String path) {
		super(context,path);
	}
	@Override
	public void open() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(url), "application/vnd.android.package-archive");
		//intent.setComponent(component)
		context.startActivity(intent);
	}

}
