package com.fmi.vveb.api;

import static com.fmi.vveb.api.StandartResponses.EMPTY_OK_RESPONSE;
import static com.fmi.vveb.api.StandartResponses.REQUIRE_AUTH_RESPONSE;
import static com.fmi.vveb.db.DbRegistry.getMembersDao;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("/members")
public class MembersApi extends BasicAuthAPI {

	@GET
	public Response getMembers() {
		try {
			authorizeUser();
			return ok().build();
		} catch (NotAuthorizedException e) {
			return REQUIRE_AUTH_RESPONSE;
		}
	}

	@POST
	public Response addMember(String memberJson) {
		JsonObject obj = new JsonParser().parse(memberJson).getAsJsonObject();
		String username = obj.get("username").getAsString();
		String password = obj.get("password").getAsString();
		getMembersDao().addMember(username, password);
		return EMPTY_OK_RESPONSE;
	}
}
