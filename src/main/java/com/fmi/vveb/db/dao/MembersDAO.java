package com.fmi.vveb.db.dao;

import static com.fmi.vveb.db.DbRegistry.getDirectoryDao;

import java.util.Base64;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fmi.vveb.db.entity.Member;

public class MembersDAO {

	private EntityManager em;

	public MembersDAO(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Member> getMembers() {
		return em.createQuery("select * from member").getResultList();
	}

	public Member getMember(long id) {
		return em.find(Member.class, id);
	}

	public Member getMember(String username) {
		Query q = em.createQuery("select m from Member m where m.username = :username");
		return (Member) q.setParameter("username", username).getSingleResult();
	}

	public void addMember(String username, String password) {
		Member m = new Member();
		m.setUsername(username);
		m.setPassword(Base64.getEncoder().encode(password.getBytes()));

		em.getTransaction().begin();
		getDirectoryDao().addDirectory("/" + username, m);
		em.persist(m);
		em.getTransaction().commit();
	}

	public void removeMember(long id) {
		em.getTransaction().begin();
		Member member = em.find(Member.class, id);
		getDirectoryDao().removeDirectory(member.getRootDirectory().getUri());
		em.remove(member);
		em.getTransaction().commit();
	}
}
