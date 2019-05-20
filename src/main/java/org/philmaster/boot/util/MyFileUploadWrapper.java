package org.philmaster.boot.util;

import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

public class MyFileUploadWrapper extends UploadedFileWrapper {



	public MyFileUploadWrapper(UploadedFile file) {
		super(file);
	}

	public String getTextFromFile() {
		return PMUtil.getTextFromFile(super.getWrapped());
	}
}
