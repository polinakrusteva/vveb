package com.fmi.vveb.repository;

import static java.util.Arrays.asList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RepositoryFacade {

	private String rootUri;

	public RepositoryFacade(String repositoryLocationUri) {
		this.rootUri = repositoryLocationUri;
	}

	public List<File> getDirectoryTree(String uri) {
		File rootFile = getRelativeFile(uri);
		if (!rootFile.exists()) {
			createDirectory(uri);
			return new ArrayList<>();
		}

		return getDirectoryTree(rootFile);
	}

	public List<File> getDirectoryTree(File f) {
		if (!f.isDirectory()) {
			return null;
		}

		return new ArrayList<>(asList(f.listFiles()));
	}

	public void createDirectoryForMember(String uri, String memberRootDir) {
		createDirectory(memberRootDir + "/" + uri);
	}

	public void createDirectory(String uri) {
		File file = getRelativeFile(uri);
		if (file.exists()) {
			return;
		}

		if (!file.mkdir()) {
			throw new IllegalStateException("Failed to create directory");
		}
	}

	public File getRelativeFile(String uri) {
		if (uri == null || uri.contains("..")) {
			throw new IllegalStateException("Invalid URI");
		}

		return new File(rootUri + "/" + uri);
	}

	public File getRelativeFileForMember(String rootDirUri, String uri) {
		return getRelativeFile(rootDirUri + "/" + uri);
	}

	public String cutPathRelativeToMemberRoot(String path, String memberRootDir) {
		if (path.length() == (this.rootUri + memberRootDir).length()) {
			return "/";
		}

		return path.substring((this.rootUri + memberRootDir).length());
	}

	public String getRootUri() {
		return rootUri;
	}
}
