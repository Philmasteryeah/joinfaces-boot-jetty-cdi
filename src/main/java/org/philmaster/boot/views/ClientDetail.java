package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.Util;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ClientDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private ObjectContext context;

	@Getter
	@Setter
	private Client detailObject;

	@Inject
	private SessionBean session;

	private static final List<String> layoutSkins = Arrays.asList("skin-blue", "skin-blue-light", "skin-yellow",
			"skin-yellow-light", "skin-green", "skin-green-light", "skin-purple", "skin-purple-light", "skin-red",
			"skin-red-light", "skin-black", "skin-black-light");

	@PostConstruct
	void init() {
		// local context
		context = DatabaseService.INSTANCE.newContext();
		// local copy of the client from session context to this view context
		detailObject = session.getLocalClient(context);

		System.err.println(context.getEntityResolver()
				.getClassDescriptorMap());

	}

	public void actionSave(ActionEvent actionEvent) {
		try {
			context.commitChanges();
		} catch (Exception e) {
			Util.statusMessageError(e.getMessage());
			return;
		}
		Util.statusMessageInfo("Saved", "Saved");
	}

	public static List<String> getLayoutSkins() {
		return layoutSkins;
	}
}
