package com.RJ.RJView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class RJOpenPDF extends RJOpenAction {
	public RJOpenPDF(Context context, String path) {
		super(context, path);
	}
	public void open() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(url), "application/pdf");
		//intent.setComponent(component)
		context.startActivity(intent);
	}

}
