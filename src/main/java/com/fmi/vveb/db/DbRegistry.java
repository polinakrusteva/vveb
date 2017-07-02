package com.fmi.vveb.db;

import javax.persistence.EntityManager;

import com.fmi.vveb.db.dao.DirectoryDAO;
import com.fmi.vveb.db.dao.MembersDAO;

// TODO: Won't work in scalable setup
public class DbRegistry {

	private static MembersDAO membersDao;
	private static DirectoryDAO directoryDao;

	public static void registerEntityManager(EntityManager em) {
		membersDao = new MembersDAO(em);
		directoryDao = new DirectoryDAO(em);
	}

	public static MembersDAO getMembersDao() {
		return membersDao;
	}

	public static DirectoryDAO getDirectoryDao() {
		return directoryDao;
	}
}
