package org.philmaster.boot.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import lombok.NonNull;

public class Util {

    // file
    
    public static String textFromFile(@NonNull UploadedFile file) {
	try {
	    Path tmpFile = Util.writeBytesToTempFile(file.getFileName(), file.getContents());
	    String text = new String(Files.readAllBytes(tmpFile), StandardCharsets.UTF_8);
	    return text;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    private static Path writeBytesToTempFile(@NonNull String filename, @NonNull byte[] bytes) throws IOException {
	final String[] filenameParts = filename.split("\\.");
	return createTempFile(filenameParts[0], filenameParts[1], bytes);
    }

    private static Path createTempFile(@NonNull String prefix, @NonNull String suffix, @NonNull byte[] bytes)
	    throws IOException {
	Path path = Files.createTempFile(prefix, "." + suffix);
	Files.write(path, bytes);
	return path;
    }

    // status message
    
    public static void statusMessageInfo(@NonNull String title, @NonNull String text) {
	statusMessage(FacesMessage.SEVERITY_INFO, title, text);
    }

    public static void statusMessageWarn(@NonNull String title, @NonNull String text) {
	statusMessage(FacesMessage.SEVERITY_WARN, title, text);
    }

    public static void statusMessageError(@NonNull String title, @NonNull String text) {
	statusMessage(FacesMessage.SEVERITY_ERROR, title, text);
    }

    private static void statusMessage(@NonNull Severity errorType, @NonNull String title, @NonNull String text) {
	FacesMessage message = new FacesMessage(errorType, title, text);
	FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
