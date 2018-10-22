package org.philmaster.boot.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

@Named
@ApplicationScoped
public class FileService {

	private ResourceLoader resourceLoader;

	// TODO
	private static String RES_STATIC_PATTERN = "classpath:static\\*.*";

	public String getFileAsString(String fileName) {
		// fileName = test.json
		List<File> files = null;
		try {
			files = getFiles();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (files == null)
			return null;
		File f = files.stream().filter(p -> p.getName().contains(fileName)).findFirst().orElse(null);
		if (f == null)
			return null;

		List<String> a = null;
		try {
			a = Files.readAllLines(f.toPath(), StandardCharsets.ISO_8859_1); // TODO
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return a.stream().collect(Collectors.joining("\n\r"));
	}

	private List<File> getFiles() throws IOException {
		// loading files from ressources inside jar
		return Arrays.stream(
				ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(RES_STATIC_PATTERN))
				.map(p -> {
					try {
						return p.getFile();
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}
				}).filter(Objects::nonNull).sorted().collect(Collectors.toList());
	}
}
