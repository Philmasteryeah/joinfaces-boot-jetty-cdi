package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.PMUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class ClientDetail {

	private static final List<String> layoutSkins = Arrays.asList("skin-blue", "skin-blue-light", "skin-yellow",
			"skin-yellow-light", "skin-green", "skin-green-light", "skin-purple", "skin-purple-light", "skin-red",
			"skin-red-light", "skin-black", "skin-black-light");

	private ObjectContext context;

	@Getter
	private Client detailObject;

//	@Inject
//	private SessionBean session;

	@PostConstruct
	void init() {
		System.err.println("init client detail");
//		context = DatabaseService.newContext();
//		detailObject = session.getLocalClient(context);

	}

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

}
