package com.fmi.vveb.db.entity;

import static javax.persistence.GenerationType.AUTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "ID", nullable = false, insertable = false)
	private long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Lob
	@Column(nullable = false, length = 100000)
	private byte[] password;

	@OneToOne
	@JoinColumn(name = "URI", nullable = false, unique = true)
	private Directory rootDirectory;

	@ManyToMany
	@JoinTable( //
			name = "ALLOWED_DIRS", //
			joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), //
			inverseJoinColumns = @JoinColumn(name = "DIR_URI", referencedColumnName = "URI")) //
	private List<Directory> allowedDirectories = new ArrayList<>();

	public Member() {
	}

	public Member(String username, byte[] password, Directory rootDirectory) {
		this.username = username;
		this.password = password;
		this.rootDirectory = rootDirectory;
	}

	public Member(String username, byte[] password, Directory rootDirectory, List<Directory> allowedDirectories) {
		this(username, password, rootDirectory);
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public Directory getRootDirectory() {
		return rootDirectory;
	}

	public void setRootDirectory(Directory rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	public List<Directory> getAllowedDirectories() {
		return allowedDirectories;
	}

	public void setAllowedDirectories(List<Directory> allowedDirectories) {
		this.allowedDirectories = allowedDirectories;
	}
}
