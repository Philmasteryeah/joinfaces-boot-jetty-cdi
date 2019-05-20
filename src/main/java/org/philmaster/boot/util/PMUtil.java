package org.philmaster.boot.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import lombok.NonNull;
import lombok.extern.java.Log;

@Log
public class PMUtil {

	private PMUtil() {
		throw new IllegalStateException("This utility class cant be instantiated.");
	}

	public static String getTextFromFile(@NonNull UploadedFile file) {
		return readTextUTF8(PMUtil.writeBytesToTempFile(file.getFileName(), file.getContents()));
	}

	public static String getTextFromFile(Path path, Charset charset) {
		try {
			return Files.readAllLines(path, charset)
					.stream()
					.collect(Collectors.joining("\n\r"));
		} catch (IOException e) {
			log.warning(e.getMessage());
		}
		return null;
	}

	public static String readTextUTF8(Path path) {
		return getTextFromFile(path, StandardCharsets.UTF_8);
	}

	public static String readTextISO(Path path) {
		return getTextFromFile(path, StandardCharsets.ISO_8859_1);
	}

	private static Path writeBytesToTempFile(@NonNull String filename, @NonNull byte[] bytes) {
		if (filename.isEmpty() || !filename.contains(".")) {
			log.warning(MessageFormat.format("filename not valid{0}", filename));
			return null;
		}
		final String[] filenameParts = filename.split("\\.");
		return createTempFile(filenameParts[0], filenameParts[1], bytes);
	}

	private static Path createTempFile(@NonNull String prefix, @NonNull String suffix, @NonNull byte[] bytes) {
		try {
			Path path = Files.createTempFile(prefix, "." + suffix);
			Files.write(path, bytes);
			return path;
		} catch (IOException e) {
			log.warning(e.getMessage());
		}
		return null;
	}

	public static String randomAlphanumericString(int length) {
		return randomAlphanumericString32().substring(0, length);
	}

	public static String randomAlphanumericString32() {

		List<char[]> characters = Arrays.asList(UUID.randomUUID()
				.toString()
				.replaceAll("-", "")
				.toCharArray());
		Collections.shuffle(characters);
		return characters.stream()
				.map(String::valueOf)
				.collect(Collectors.joining());// alphanumeric - 32
	}

	public static void statusMessageInfo(@NonNull String text) {
		statusMessageInfo("INFO", text);
	}

	public static void statusMessageInfo(@NonNull String title, @NonNull String text) {
		statusMessage(FacesMessage.SEVERITY_INFO, title, text);
		log.info(text);
	}

	public static void statusMessageWarn(@NonNull String text) {
		statusMessageWarn("WARN", text);
	}

	public static void statusMessageWarn(@NonNull String title, @NonNull String text) {
		statusMessage(FacesMessage.SEVERITY_WARN, title, text);
		log.warning(text);
	}

	public static void statusMessageError(@NonNull String text) {
		statusMessageError("ERROR", text);
	}

	public static void statusMessageError(@NonNull String title, @NonNull String text) {
		statusMessage(FacesMessage.SEVERITY_ERROR, title, text);
		log.warning(text);
	}

	private static void statusMessage(@NonNull Severity errorType, @NonNull String title, @NonNull String text) {
		FacesMessage message = new FacesMessage(errorType, title, text);
		FacesContext.getCurrentInstance()
				.addMessage(null, message);
	}

	public static List<Field> getAllFields(@NonNull List<Field> arrayList, @NonNull Class<?> type) {
		arrayList.addAll(Arrays.asList(type.getDeclaredFields()));
		if (type.getSuperclass() != null)
			getAllFields(arrayList, type.getSuperclass());
		return arrayList;
	}

	public static Object getAccessibleField(@NonNull List<Field> fields, String sortField, Object obj) {

		Field field = fields.stream()
				.filter(f -> f.getName()
						.replaceAll(".+\\.", "")
						.equals(sortField))
				.findFirst()
				.orElse(null);
		if (field == null) {
			log.warning(MessageFormat.format("field {0} not found", sortField));
			return null;
		}
		field.setAccessible(true); // cayennes fields are protected, so we need this ultra evil hack
		try {
			return field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.warning(e.getMessage());
			return null;
		}
	}
}
