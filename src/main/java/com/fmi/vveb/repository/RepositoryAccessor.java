package com.fmi.vveb.repository;

import java.io.File;

public class RepositoryAccessor {

	private static RepositoryFacade facade;

	public static void initializeRepositoryFacade(String rootUri) {
		createRepository(rootUri);
		facade = new RepositoryFacade(rootUri);
	}

	private static void createRepository(String rootUri) {
		File root = new File(rootUri);
		if (root.exists()) {
			return;
		}

		if (!root.mkdir()) {
			throw new IllegalStateException("Failed to create repository root");
		}
	}

	public static RepositoryFacade getFacade() {
		return facade;
	}
}
