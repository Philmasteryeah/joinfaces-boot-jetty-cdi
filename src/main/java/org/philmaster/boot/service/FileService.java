package org.philmaster.boot.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
			System.out.println(e);
		}
		return null;
	}

	private URI resourceToUri(Resource resource) {
		try {
			return resource.getURI();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

}
