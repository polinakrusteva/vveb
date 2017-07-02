package com.fmi.vveb.api;

import static com.fmi.vveb.db.DbRegistry.getMembersDao;

import java.util.Base64;

import javax.ws.rs.HeaderParam;

import com.fmi.vveb.db.entity.Member;

// TODO: Should be CommonAPI and have composed authentication strategy
public class BasicAuthAPI {

	private static final String BASIC = "Basic";

	@HeaderParam("authorization")
	private String auth;

	protected Member lookupAndAuthorizeUser() throws NotAuthorizedException {
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

		return member;
	}
}
