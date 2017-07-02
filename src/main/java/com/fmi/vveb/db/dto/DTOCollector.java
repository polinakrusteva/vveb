package com.fmi.vveb.db.dto;

import static com.fmi.vveb.db.DbRegistry.getMembersDao;
import static com.fmi.vveb.repository.RepositoryAccessor.getFacade;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
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

	public static List<FileDTO> getFiles(String uri, String memberRootDir) {
		List<File> directoryTree = getFacade().getDirectoryTree(uri);
		if (directoryTree == null) {
			return new ArrayList<>();
		}

		return directoryTree.stream()
				.map(f -> new FileDTO(f.getName(), f.isDirectory(),
						encode(getFacade().cutPathRelativeToMemberRoot(f.getAbsolutePath(), memberRootDir))))
				.collect(toList());
	}

	public static List<FileDTO> getFiles(File file, String memberRootDir) {
		List<File> directoryTree = getFacade().getDirectoryTree(file);
		if (directoryTree == null) {
			return new ArrayList<>();
		}

		return directoryTree.stream()
				.map(f -> new FileDTO(f.getName(), f.isDirectory(),
						encode(getFacade().cutPathRelativeToMemberRoot(f.getAbsolutePath(), memberRootDir))))
				.collect(toList());
	}

	public static DirectoryDTO getDirectory(String uri, String memberRootDir) {
		File f = getFacade().getRelativeFile(uri);
		return new DirectoryDTO(encode(getFacade().cutPathRelativeToMemberRoot(f.getAbsolutePath(), memberRootDir)),
				getFiles(uri, memberRootDir));
	}

	public static DirectoryDTO getDirectory(File file, String memberRootDir) {
		return new DirectoryDTO(encode(getFacade().cutPathRelativeToMemberRoot(file.getAbsolutePath(), memberRootDir)),
				getFiles(file, memberRootDir));
	}

	private static String encode(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes());
	}
}
