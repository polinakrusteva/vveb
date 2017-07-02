package com.fmi.vveb.db.dto;

import java.util.List;

public class DirectoryDTO {

	private String currentDir;
	private List<FileDTO> content;

	public DirectoryDTO(String currentDir, List<FileDTO> content) {
		this.currentDir = currentDir;
		this.content = content;
	}

	public String getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(String currentDir) {
		this.currentDir = currentDir;
	}

	public List<FileDTO> getContent() {
		return content;
	}

	public void setContent(List<FileDTO> content) {
		this.content = content;
	}
}
