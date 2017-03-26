package org.philmaster.boot.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
	// Java 7 for Heroku -.-
	try {
	    BufferedReader buffer = new BufferedReader(new InputStreamReader(file.getInputstream()));
	    setEditorText(buffer.lines().collect(Collectors.joining("\n")));
	    buffer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
