package org.philmaster.boot.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.MessageFormat;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.philmaster.boot.util.PMUtil;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;

@Log
@Named
@ApplicationScoped
public class FileService implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public static <T> T mapResourceToObject(Resource resource, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(resource.getInputStream(), clazz);
		} catch (IOException e) {
			log.warning(e.getMessage());
		}
		return null;
	}

	public static <T> T mapInputStreamToObject(InputStream is, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(is, clazz);
		} catch (IOException e) {
			log.warning(e.getMessage());
		}
		return null;
	}

	private Resource getStaticResource(String filename) {

		log.info(MessageFormat.format("loading file {0}", filename));
		return resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "static/" + filename);
	}

	public String getStaticFileAsString(String filename) {
		URI uri = resourceToUri(getStaticResource(filename));
		if (uri == null)
			return null;
		return PMUtil.readTextISO(Path.of(uri));
	}

	private static URI resourceToUri(Resource resource) {
		try {
			return resource.getURI();
		} catch (FileNotFoundException e) {
			log.warning(MessageFormat.format("file not found {0}", e.getMessage()));
		} catch (IOException e) {
			log.warning(MessageFormat.format("cant load file {0}", e.getMessage()));
		}
		return null;
	}

	/**
	 * watch a directory for changes
	 * 
	 * @param fullPath
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void watchDir(String fullPath) throws IOException, InterruptedException {
		try (WatchService watchService = FileSystems.getDefault()
				.newWatchService()) {
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
		} catch (Exception e) {
		}
	}

}
