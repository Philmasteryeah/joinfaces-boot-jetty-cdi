package org.philmaster.boot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class Util {

    // TODO this should be java.nio
    public static File writeStreamToTempFile(InputStream in, String filename) throws IOException {
	if (in == null || filename == null)
	    return null;
	final String[] filenameParts = filename.split("\\.");
	// Path tmpFile = Files.createTempFile(dir, prefix, suffix, attrs)
	final File tempFile = File.createTempFile(filenameParts[0], filenameParts[1]);
	tempFile.deleteOnExit();
	try (FileOutputStream out = new FileOutputStream(tempFile)) {
	    IOUtils.copy(in, out);
	}
	return tempFile;
    }
}
