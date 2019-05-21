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
import org.philmaster.boot.util.PMUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ClientDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final List<String> layoutSkins = Arrays.asList("skin-blue", "skin-blue-light", "skin-yellow",
			"skin-yellow-light", "skin-green", "skin-green-light", "skin-purple", "skin-purple-light", "skin-red",
			"skin-red-light", "skin-black", "skin-black-light");

	private ObjectContext context;

	@Setter
	private Client detailObject;

	@Inject
	private SessionBean session;

	@PostConstruct
	void init() {

	}
	// TODO fetch client new and save only name in session
	
	public void actionSave(ActionEvent actionEvent) {
		try {
			context.commitChanges();
		} catch (Exception e) {
			PMUtil.statusMessageError(e.getMessage());
			return;
		}
		PMUtil.statusMessageInfo("Saved", "Saved");
	}

	public static List<String> getLayoutSkins() {
		return layoutSkins;
	}

	public ObjectContext getContext() {
		if (context == null)
			context = DatabaseService.INSTANCE.newContext();
		return context;
	}

	public Client getDetailObject() {
		if (detailObject == null)
			detailObject = session.getLocalClient(getContext());
		return detailObject;
	}
}
