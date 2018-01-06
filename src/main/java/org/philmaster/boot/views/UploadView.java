package org.philmaster.boot.views;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.util.MyFileUploadWrapper;
import org.philmaster.boot.util.Util;
import org.primefaces.model.UploadedFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class UploadView implements Serializable {

	private static final long serialVersionUID = 1L;

	private UploadedFile file;

	private String editorText;

	public void upload() {
		if (file == null) {
			Util.statusMessageError("Error", "File could not be uploaded.");
			return;
		}
		MyFileUploadWrapper wrapper = new MyFileUploadWrapper(file);

		String text = wrapper.getTextFromFile();
		if (text == null) {
			Util.statusMessageError("Error", "File could not be read.");
			return;
		}
		Util.statusMessageInfo("Succesful", file.getFileName() + " was uploaded.");

		setEditorText(text);
	}
}
