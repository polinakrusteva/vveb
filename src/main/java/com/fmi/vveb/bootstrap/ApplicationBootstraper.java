package com.fmi.vveb.bootstrap;

import static com.fmi.vveb.db.DbRegistry.registerEntityManager;

import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationBootstraper implements ServletContextListener {

	public void contextInitialized(ServletContextEvent ctx) {
		registerEntityManager(Persistence.createEntityManagerFactory("vveb").createEntityManager());
	}
}
