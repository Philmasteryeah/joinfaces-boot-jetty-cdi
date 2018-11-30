package org.philmaster.boot.views;

import java.io.Serializable;

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

	@Inject
	private DatabaseService db;
	
	private ObjectContext context;

	@Getter
	@Setter
	private Client detailObject;

	@Inject
	private SessionBean session;

	@PostConstruct
	void init() {
		context = db.newContext();
		detailObject = session.getClient(context);
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();
		Util.statusMessageInfo("Saved", "Saved");
	}
}
