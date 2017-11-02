package org.philmaster.boot.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {

    public static Path writeBytesToTempFile(String filename, byte[] bytes) throws IOException {
	if (bytes == null || filename == null)
	    return null;
	final String[] filenameParts = filename.split("\\.");
	return createTempFile(filenameParts[0], filenameParts[1], bytes);
    }

    private static Path createTempFile(String prefix, String suffix, byte[] bytes) throws IOException {
	Path path = Files.createTempFile(prefix, "." + suffix);
	Files.write(path, bytes);
	return path;
    }

}
