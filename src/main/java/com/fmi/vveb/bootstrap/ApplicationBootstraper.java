package com.fmi.vveb.bootstrap;

import static com.fmi.vveb.db.DbRegistry.registerEntityManager;
import static com.fmi.vveb.repository.RepositoryAccessor.initializeRepositoryFacade;

import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationBootstraper implements ServletContextListener {

	public void contextInitialized(ServletContextEvent e) {
		initializeRepositoryFacade("/Users/miro/repo");
		registerEntityManager(Persistence.createEntityManagerFactory("vveb").createEntityManager());
	}
}
