package com.fmi.vveb.api;

import static com.fmi.vveb.api.StandartResponses.REQUIRE_AUTH_RESPONSE;
import static com.fmi.vveb.db.dto.DTOCollector.getFiles;
import static com.fmi.vveb.repository.RepositoryAccessor.getFacade;
import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.ok;

import java.io.File;
import java.util.Base64;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.fmi.vveb.db.entity.Member;

@Path("/contents")
@Produces({ APPLICATION_JSON, TEXT_PLAIN })
public class RepositoryApi extends BasicAuthAPI {

	@GET
	@Produces(APPLICATION_JSON)
	public Response getRootContents() {
		try {
			Member member = lookupAndAuthorizeUser();
			return ok(getFiles(member.getRootDirectory().getUri(), member.getRootDirectory().getUri())).build();
		} catch (NotAuthorizedException e) {
			return REQUIRE_AUTH_RESPONSE;
		}
	}

	@GET
	@Path("{encodedPath}")
	public Response getDirectoryContent(@PathParam("encodedPath") String encodedPath) {
		try {
			Member member = lookupAndAuthorizeUser();
			String pathName = new String(Base64.getDecoder().decode(encodedPath));
			File f = getFacade().getRelativeFileForMember(member.getRootDirectory().getUri(), pathName);
			if (f.isDirectory()) {
				return ok(getFiles(f, member.getRootDirectory().getUri())).build();
			}

			ResponseBuilder response = Response.ok((Object) f);
			response.header("Content-Disposition", format("attachment; filename=\"%s\"", f.getName()));
			return response.build();
		} catch (NotAuthorizedException e) {
			return REQUIRE_AUTH_RESPONSE;
		}
	}
}
