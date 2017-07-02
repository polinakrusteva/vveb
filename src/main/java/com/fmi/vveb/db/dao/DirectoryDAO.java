package com.fmi.vveb.db.dao;

import javax.persistence.EntityManager;

import com.fmi.vveb.db.entity.Directory;
import com.fmi.vveb.db.entity.Member;

public class DirectoryDAO {

	private EntityManager em;

	public DirectoryDAO(EntityManager em) {
		this.em = em;
	}

	public Directory getDirectory(String uri) {
		return em.find(Directory.class, uri);
	}

	public void addDirectoryTransactional(String uri, Member owner) {
		em.getTransaction().begin();
		addDirectory(uri, owner);
		em.getTransaction().commit();
	}

	public void addDirectory(String uri, Member owner) {
		Directory d = new Directory();
		d.setUri(uri);
		d.setOwner(owner);
		owner.setRootDirectory(d);
		em.persist(d);
	}

	public void removeDirectory(String uri) {
		em.getTransaction().begin();
		Directory dir = em.find(Directory.class, uri);
		em.remove(dir);
		em.getTransaction().commit();
	}
}
