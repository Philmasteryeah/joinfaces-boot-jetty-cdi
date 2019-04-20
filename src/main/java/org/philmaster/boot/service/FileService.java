package org.philmaster.boot.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
		List<Path> files = null;
		try {
			files = getSaticResourceFiles();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (files == null)
			return null;

		Path path = files.stream()
				.filter(p -> p.compareTo(Path.of(fileName)) == 0)
				.findFirst()
				.orElse(null);

		if (path == null)
			return null;

		try {
			return Files.readAllLines(path, StandardCharsets.ISO_8859_1)
					.stream()
					.collect(Collectors.joining("\n\r"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Path> getSaticResourceFiles() throws IOException {
		// loading files from ressources inside jar
		// only works with iput streams, not wit file !!!
		return Arrays.stream(ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
				.getResources(RES_STATIC_PATTERN))
				.map(p -> Path.of(p.getFilename()))
				.filter(Objects::nonNull)
				.sorted()
				.collect(Collectors.toList());
	}
}
