package com.RJ.RJView;

import java.io.File;
import java.util.ArrayList;

import android.graphics.Bitmap;

public class RJGridCell implements Comparable<RJGridCell>{
	private String name;
	private int type; //File type
	private String description; 
	private Bitmap icon;
	private File file;
	private int flag; //0 dir, 1 file
	private String path;
	private ArrayList<RJGridCell> subfiles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Bitmap getIcon() {
		return icon;
	}
	public void setIcon(Bitmap icon) {
		this.icon = icon;
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

//TODO finish this function
	public RJGridCell(File file) {
		this.file = file;
		this.path = file.getAbsolutePath();
		this.name = file.getName();
		//this.type = file.
	}
	
	@Override
	public int compareTo(RJGridCell another) {
		return (this.name.compareTo(another.getName()));
	}
	public boolean isDir() {
		return file.exists() && file.isDirectory();
	}
}
