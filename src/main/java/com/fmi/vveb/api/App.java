package com.fmi.vveb.api;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
public class App extends Application {

	private static final Class<?>[] classes = new Class<?>[] { //
			MembersApi.class, //
			RepositoryApi.class //
	};

	public Set<Class<?>> getClasses() {
		return new HashSet<>(asList(classes));
	}
}
