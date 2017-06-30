package com.fmi.vveb.api;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class Test {

	@GET
	public Response getTest() {
		EntityManager em = Persistence.createEntityManagerFactory("vveb").createEntityManager();
		em.createNativeQuery("SELECT * FROM TESTENTITY");
		return Response.ok().build();
	}
}
