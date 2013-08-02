package com.RJ.RJView;

import java.io.File;
import java.util.ArrayList;

public class RJGridCell implements Comparable<RJGridCell>{
	private String name;
	private FileType type; //File type
	private String description; 
	private File file;
	private int flag; //0 dir, 1 file
	private String path;
	private ArrayList<RJGridCell> subfiles;
	
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
	public RJGridCell(File file) {
		this.file = file;
		this.setPath(file.getAbsolutePath());
		this.name = file.getName();
		if (file.isDirectory()) {
			this.flag = 0;
		}
	}
	
	@Override
	public int compareTo(RJGridCell another) {
		return (this.name.compareTo(another.getName()));
	}
	
}
