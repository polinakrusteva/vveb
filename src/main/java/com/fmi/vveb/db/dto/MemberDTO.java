package com.fmi.vveb.db.dto;

import java.util.List;

public class MemberDTO {

	private long id;
	private String username;
	private String rootDir;
	private List<String> allowedDirectories;

	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(long id, String username, String rootDir, List<String> allowedDirectories) {
		this.id = id;
		this.username = username;
		this.rootDir = rootDir;
		this.allowedDirectories = allowedDirectories;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public List<String> getAllowedDirectories() {
		return allowedDirectories;
	}

	public void setAllowedDirectories(List<String> allowedDirectories) {
		this.allowedDirectories = allowedDirectories;
	}
}
