package com.RJ.RJView;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;

import com.RJ.RJFileOps.RJOpenAPK;
import com.RJ.RJFileOps.RJOpenAction;
import com.RJ.RJFileOps.RJOpenAudio;
import com.RJ.RJFileOps.RJOpenCHM;
import com.RJ.RJFileOps.RJOpenDOC;
import com.RJ.RJFileOps.RJOpenDefault;
import com.RJ.RJFileOps.RJOpenImage;
import com.RJ.RJFileOps.RJOpenPDF;
import com.RJ.RJFileOps.RJOpenPPT;
import com.RJ.RJFileOps.RJOpenTXT;
import com.RJ.RJFileOps.RJOpenVideo;
import com.RJ.RJFileOps.RJOpenXLS;

public class RJGridCell implements Comparable<RJGridCell>{
	private String name;
	private FileType type; //File type
	private RJOpenAction action;
	private String description; 
	private File file;
	private int flag; //0 dir, 1 file
	private String path;
	private ArrayList<RJGridCell> subfiles;
	private Context context;
	
	enum FileType{
		AUDIO,
		VIDEO,
		IMAGE,
		APK,
		DOC,
		XLS,
		PPT,
		TXT,
		PDF,
		CHM,
		DEFAULT
	};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FileType getType() {
		return type;
	}
	public void setType(FileType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public ArrayList<RJGridCell> getSubfiles() {
		return subfiles;
	}
	public void setSubfiles(ArrayList<RJGridCell> subfiles) {
		this.subfiles = subfiles;
	}
	
	public boolean isDir() {
		return file.exists() && file.isDirectory();
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

//TODO finish this function
	public RJGridCell(Context context, File file) {
		this.context = context;
		this.file = file;
		this.setPath(file.getAbsolutePath());
		this.name = file.getName();
		if (file.isDirectory()) {
			this.flag = 0; //IS DIR
		} else {
			this.flag = 1; // IS File
			setFileActionByType(name);
		}
	}
	
	private void setFileActionByType(String name) {
		String typeString;
		int start = name.lastIndexOf('.');
		if (start > -1) 
			typeString = name.substring(start, name.length());
		else 
			typeString = "";
		if(typeString.equals("m4a")||typeString.equals("mp3")||typeString.equals("mid")||
				typeString.equals("xmf")||typeString.equals("ogg")||typeString.equals("wav")){
			type = FileType.AUDIO;
			action = new RJOpenAudio(context, path);
		}else if(typeString.equals("3gp")||typeString.equals("mp4")){
			type = FileType.VIDEO; 
			this.action = new RJOpenVideo(context, path);
		}else if(typeString.equals("jpg")||typeString.equals("gif")||typeString.equals("png")||
				typeString.equals("jpeg")||typeString.equals("bmp")){
			type = FileType.IMAGE;
			action = new RJOpenImage(context, path);
		}else if(typeString.equals("apk")){
			type = FileType.APK;
			action = new RJOpenAPK(context, path);
		}else if(typeString.equals("ppt")||typeString.equals("pptx")){
			type = FileType.PPT;
			action = new RJOpenPPT(context, path);
		}else if(typeString.equals("xls")||typeString.equals("xlsx")){
			type = FileType.XLS;
			action = new RJOpenXLS(context, path);
		}else if(typeString.equals("doc")||typeString.equals("docx")){
			type = FileType.DOC;
			action = new RJOpenDOC(context, path);
		}else if(typeString.equals("pdf")){
			type = FileType.PDF;
			action = new RJOpenPDF(context, path);
		}else if(typeString.equals("chm")){
			type = FileType.CHM;
			action = new RJOpenCHM(context, path);
		}else if(typeString.equals("txt")){
			type = FileType.TXT;
			action = new RJOpenTXT(context, path);
		}else{
			type = FileType.DEFAULT;
			action = new RJOpenDefault(context, path);
		}
		
	}
	
	public void openfile() {
		if (action != null)
			action.open();
	}
	
	@Override
	public int compareTo(RJGridCell another) {
		return (this.name.compareTo(another.getName()));
	}
	
	
}
