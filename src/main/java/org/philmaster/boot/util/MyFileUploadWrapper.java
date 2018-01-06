package org.philmaster.boot.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

public class MyFileUploadWrapper extends UploadedFileWrapper {

	// handle the shitty uploadedFile here

	public MyFileUploadWrapper(UploadedFile file) {
		super(file);
	}

	public String getTextFromFile() {
		try {
			Path tmpFile = Util.writeBytesToTempFile(getFileName(), getContents());
			if (tmpFile == null)
				return null;
			return new String(Files.readAllBytes(tmpFile), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
