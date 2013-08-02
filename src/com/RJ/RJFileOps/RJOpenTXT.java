package com.RJ.RJFileOps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class RJOpenTXT extends RJOpenAction {
	public RJOpenTXT(Context context, String path) {
		super(context, path);
	}
	@Override
	public void open() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(url), "text/plain");
		//intent.setComponent(component)
		context.startActivity(intent);
	}

}
