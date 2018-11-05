package org.philmaster.boot.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.UploadedFile;

import lombok.NonNull;

public class Util {

	private static final Logger LOGGER = LogManager.getLogger();

	// file

	public static String textFromFile(@NonNull UploadedFile file) {
		try {
			Path tmpFile = Util.writeBytesToTempFile(file.getFileName(), file.getContents());
			if (tmpFile == null)
				return null;
			String text = new String(Files.readAllBytes(tmpFile), StandardCharsets.UTF_8);
			return text;
		} catch (IOException e) {
			System.err.println(e); // TODO
		}
		return null;
	}

	public static Path writeBytesToTempFile(@NonNull String filename, @NonNull byte[] bytes) throws IOException {
		if (filename.isEmpty() || !filename.contains("."))
			return null;
		final String[] filenameParts = filename.split("\\.");
		return createTempFile(filenameParts[0], filenameParts[1], bytes);
	}

	private static Path createTempFile(@NonNull String prefix, @NonNull String suffix, @NonNull byte[] bytes)
			throws IOException {
		Path path = Files.createTempFile(prefix, "." + suffix);
		Files.write(path, bytes);
		return path;
	}

	public static String randomAlphanumericString(int length) {
		return randomAlphanumericString32().substring(0, length);
	}

	public static String randomAlphanumericString32() {
		// 2d7428a6-b58c-4008-8575-f05549f16316 - 36
		List<char[]> characters = Arrays.asList(UUID.randomUUID().toString().replaceAll("-", "").toCharArray());
		Collections.shuffle(characters);
		return characters.stream().map(c -> String.valueOf(c)).collect(Collectors.joining());// alphanumeric - 32
	}

	// status message

	public static void statusMessageInfo(@NonNull String title, @NonNull String text) {
		statusMessage(FacesMessage.SEVERITY_INFO, title, text);
		LOGGER.info(text);
	}

	public static void statusMessageWarn(@NonNull String title, @NonNull String text) {
		statusMessage(FacesMessage.SEVERITY_WARN, title, text);
		LOGGER.warn(text);
	}

	public static void statusMessageError(@NonNull String title, @NonNull String text) {
		statusMessage(FacesMessage.SEVERITY_ERROR, title, text);
		LOGGER.error(text);
	}

	private static void statusMessage(@NonNull Severity errorType, @NonNull String title, @NonNull String text) {
		FacesMessage message = new FacesMessage(errorType, title, text);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// reflection

	public static List<Field> getAllFields(@NonNull List<Field> arrayList, @NonNull Class<?> type) {
		arrayList.addAll(Arrays.asList(type.getDeclaredFields()));
		if (type.getSuperclass() != null)
			getAllFields(arrayList, type.getSuperclass());
		return arrayList;
	}

	public static Object getAccessibleField(@NonNull List<Field> fields, String sortField, Object obj) {
		// field = java.lang.String org.philmaster.boot.model.auto._Car.name
		// sortField = name
		Field field = fields.stream().filter(f -> f.getName().replaceAll(".+\\.", "").equals(sortField)).findFirst()
				.orElse(null);
		if (field == null) {
			LOGGER.info("field " + field + " not found");
			return null;
		}
		field.setAccessible(true); // cayennes fields are protected, so we need this hack
		try {
			return field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.err.println(e); // TODO
			return null;
		}
	}
}
