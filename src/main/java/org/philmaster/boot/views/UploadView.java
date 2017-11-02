package org.philmaster.boot.views;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.util.Util;
import org.primefaces.model.UploadedFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class UploadView {

    private UploadedFile file;

    private String editorText;

    public void upload() {
	FacesMessage message;
	if (file == null || file.getFileName().isEmpty()) {
	    message = new FacesMessage("Error", "File could not be uploaded.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return;
	}
	message = new FacesMessage("Succesful", file.getFileName() + " was uploaded.");
	FacesContext.getCurrentInstance().addMessage(null, message);

	try {
	    File tmpFile = Util.writeStreamToTempFile(file.getInputstream(), file.getFileName());
	    String text = new String(Files.readAllBytes(Paths.get(tmpFile.toURI())), StandardCharsets.UTF_8);
	    setEditorText(text);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
