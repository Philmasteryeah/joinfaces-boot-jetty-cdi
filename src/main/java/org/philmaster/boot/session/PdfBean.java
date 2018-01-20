package org.philmaster.boot.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class PdfBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private StreamedContent pdfViewerContent;

	public void onPrerender(ComponentSystemEvent event) {

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			Document document = new Document();
			PdfWriter.getInstance(document, out);
			document.open();

			for (int i = 0; i < 50; i++) {
				document.add(new Paragraph("All work and no play makes Jack a dull boy"));
			}

			document.close();
			pdfViewerContent = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()),
					"application/pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
