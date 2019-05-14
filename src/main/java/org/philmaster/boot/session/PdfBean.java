package org.philmaster.boot.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Named
@ViewScoped
public class PdfBean implements Serializable {

	@Getter
	@Setter
	private transient StreamedContent pdfViewerContent;

	public void onPrerender(ComponentSystemEvent event) {

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();

			for (int i = 0; i < 50; i++) {
				document.add(new Paragraph("All work and no play makes Jack a dull boy"));
			}

			document.close();
			writer.close();
			pdfViewerContent = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()),
					"application/pdf");
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
	}
}
