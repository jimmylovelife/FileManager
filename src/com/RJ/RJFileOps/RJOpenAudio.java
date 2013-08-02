package com.RJ.RJFileOps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class RJOpenAudio extends RJOpenAction {
	
	public RJOpenAudio(Context context, String path) {
		super(context, path);
	}

	public void open() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(url), "audio/*");
		//intent.setComponent(component)
		context.startActivity(intent);
	}

}
