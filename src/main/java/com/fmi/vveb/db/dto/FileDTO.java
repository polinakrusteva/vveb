package com.fmi.vveb.db.dto;

public class FileDTO {

	private String name;
	private boolean isDirectory;
	private String encodedPath;

	public FileDTO(String name, boolean isDirectory, String encodedPath) {
		this.name = name;
		this.isDirectory = isDirectory;
		this.encodedPath = encodedPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String uri) {
		this.name = uri;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public String getEncodedPath() {
		return encodedPath;
	}

	public void setEncodedPath(String encodedPath) {
		this.encodedPath = encodedPath;
	}
}
