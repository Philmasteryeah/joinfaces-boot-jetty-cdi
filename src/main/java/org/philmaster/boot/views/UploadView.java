package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.util.MyFileUploadWrapper;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.model.UploadedFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@RequestScoped
public class UploadView {

	private UploadedFile file;

	private String editorText;

	public void upload() {
		if (file == null) {
			PMUtil.statusMessageError("Error", "File could not be uploaded.");
			return;
		}
		MyFileUploadWrapper wrapper = new MyFileUploadWrapper(file);

		String text = wrapper.getTextFromFile();
		if (text == null) {
			PMUtil.statusMessageError("Error", "File could not be read.");
			return;
		}
		PMUtil.statusMessageInfo("Succesful", file.getFileName() + " was uploaded.");

		setEditorText(text);
	}
}
