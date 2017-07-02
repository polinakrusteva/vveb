package com.fmi.vveb.api;

import static com.fmi.vveb.db.DbRegistry.getMembersDao;

import java.util.Base64;

import javax.ws.rs.HeaderParam;

import com.fmi.vveb.db.entity.Member;

public class BasicAuthAPI {

	private static final String BASIC = "Basic";

	@HeaderParam("authorization")
	protected String auth;

	protected void authorizeUser() throws NotAuthorizedException {
		if (auth == null || !auth.startsWith(BASIC)) {
			throw new NotAuthorizedException();
		}

		String encodedAuth = auth.substring(BASIC.length()).trim();
		byte[] decodedData = Base64.getDecoder().decode(encodedAuth);
		String authenticationData = new String(decodedData);
		String[] auth = authenticationData.split(":");
		if (auth.length != 2) {
			throw new NotAuthorizedException();
		}

		String username = auth[0];
		String password = auth[1];
		Member member = getMembersDao().getMember(username);
		if (!password.equals(new String(Base64.getDecoder().decode(member.getPassword())))) {
			throw new NotAuthorizedException();
		}
	}
}
