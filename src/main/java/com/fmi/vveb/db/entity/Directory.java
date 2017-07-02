package com.fmi.vveb.db.entity;

import static javax.persistence.CascadeType.ALL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Directory {

	@Id
	@Column(nullable = false)
	private String uri;

	@OneToOne(mappedBy = "rootDirectory", cascade = ALL)
	private Member owner;

	@ManyToMany(mappedBy = "allowedDirectories")
	private Set<Member> guests = new HashSet<>();

	public Directory() {
	}

	public Directory(String uri, Member owner) {
		this.uri = uri;
		this.owner = owner;
	}

	public Directory(String uri, Member owner, List<Member> guests) {
		this(uri, owner);
		this.guests = new HashSet<>(guests);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}

	public Set<Member> getGuests() {
		return guests;
	}

	public void setGuests(Set<Member> guests) {
		this.guests = guests;
	}
}
