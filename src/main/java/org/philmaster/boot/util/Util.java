package org.philmaster.boot.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.primefaces.model.UploadedFile;

public class Util {

    public static String textFromFile(UploadedFile file) {
	if (file == null)
	    return null;
	try {
	    Path tmpFile = Util.writeBytesToTempFile(file.getFileName(), file.getContents());
	    String text = new String(Files.readAllBytes(tmpFile), StandardCharsets.UTF_8);
	    return text;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    private static Path writeBytesToTempFile(String filename, byte[] bytes) throws IOException {
	if (filename == null || bytes == null)
	    return null;
	final String[] filenameParts = filename.split("\\.");
	return createTempFile(filenameParts[0], filenameParts[1], bytes);
    }

    private static Path createTempFile(String prefix, String suffix, byte[] bytes) throws IOException {
	if (prefix == null || suffix == null || bytes == null)
	    return null;
	Path path = Files.createTempFile(prefix, "." + suffix);
	Files.write(path, bytes);
	return path;
    }
}
