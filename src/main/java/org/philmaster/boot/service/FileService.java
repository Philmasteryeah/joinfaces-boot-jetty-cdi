package org.philmaster.boot.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Named
@ApplicationScoped
public class FileService implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	private Resource getStaticResource(String filename) {
		return resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "static/" + filename);
	}

	public String getStaticFileAsString(String filename) {
		Resource resource = getStaticResource(filename);
		System.err.println("resource loaded " + resource);

		URI uri = resourceToUri(resource);
		if (uri == null)
			return null;

		try {
			return Files.readAllLines(Path.of(uri), StandardCharsets.ISO_8859_1)
					.stream()
					.collect(Collectors.joining("\n\r"));
		} catch (IOException e) {
			System.err.println(e);
		}
		return null;
	}

	private URI resourceToUri(Resource resource) {
		try {
			return resource.getURI();
		} catch (FileNotFoundException e) {
			System.err.println("File not found " + e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Observe a directory for changes
	 * 
	 * @param fullPath
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void watchDir(String fullPath) throws IOException, InterruptedException {
		WatchService watchService = FileSystems.getDefault()
				.newWatchService();
		// fullPath = "C:\\import"
		Path path = Paths.get(fullPath);

		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				System.err.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
			}
			key.reset();
		}
	}

}
