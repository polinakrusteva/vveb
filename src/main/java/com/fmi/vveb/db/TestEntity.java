package com.fmi.vveb.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestEntity {

	@Column
	@Id
	private long id;
	
}
