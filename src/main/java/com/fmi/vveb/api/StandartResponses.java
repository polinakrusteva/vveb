package com.fmi.vveb.api;

import javax.ws.rs.core.Response;

public class StandartResponses {

	public static final Response REQUIRE_AUTH_RESPONSE = //
			Response.status(401).header("WWW-Authenticate", "Basic").build();

	public static final Response EMPTY_OK_RESPONSE = //
			Response.ok().build();
}
