package com.fmi.vveb.db.dto;

import static com.fmi.vveb.db.DbRegistry.getMembersDao;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.fmi.vveb.db.entity.Directory;

public class DTOCollector {

	public static List<MemberDTO> getMembers() {
		return getMembersDao().getMembers().stream().map( //
				m -> new MemberDTO(m.getId(), m.getUsername(), m.getRootDirectory().getUri(), //
						collectDirectoriesUris(m.getAllowedDirectories()) //
				)).collect(toList());
	}

	private static List<String> collectDirectoriesUris(List<Directory> dirs) {
		return dirs.stream().map(d -> d.getUri()).collect(toList());
	}
}
