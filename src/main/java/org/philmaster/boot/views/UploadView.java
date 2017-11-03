package org.philmaster.boot.views;

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
	if (file == null || file.getFileName().isEmpty()) {
	    Util.statusMessageError("Error", "File could not be uploaded.");
	    return;
	}

	Util.statusMessageInfo("Succesful", file.getFileName() + " was uploaded.");

	setEditorText(Util.textFromFile(file));

    }
}
