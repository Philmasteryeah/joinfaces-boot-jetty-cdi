package org.philmaster.boot.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class FileUploadView {

    @Inject
    private EditorView editor;

    private UploadedFile file;

    public UploadedFile getFile() {
	return file;
    }

    public void setFile(UploadedFile file) {
	this.file = file;
    }

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
	    editor.setText(buffer.lines().collect(Collectors.joining("\n")));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	// Java 8
	// try (BufferedReader buffer = new BufferedReader(new
	// InputStreamReader(file.getInputstream()))) {
	// editor.setText(buffer.lines().collect(Collectors.joining("\n")));
	// } catch (IOException e) {
	// // TODO
	// e.printStackTrace();
	// }

    }
}
