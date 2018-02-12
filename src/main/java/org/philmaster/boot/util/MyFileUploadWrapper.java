package org.philmaster.boot.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

public class MyFileUploadWrapper extends UploadedFileWrapper {

	private static final Logger LOGGER = LogManager.getLogger();

	// file.nio wrapper for file.io

	public MyFileUploadWrapper(UploadedFile file) {
		super(file);
	}

	public String getTextFromFile() {
		try {
			Path tmpFile = Util.writeBytesToTempFile(getFileName(), getContents());
			if (tmpFile == null) {
				LOGGER.info("file not found");
				return null;
			}
			return new String(Files.readAllBytes(tmpFile), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
