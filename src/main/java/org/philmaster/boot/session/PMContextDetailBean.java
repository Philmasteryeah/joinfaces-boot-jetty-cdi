package org.philmaster.boot.session;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;

import lombok.Getter;

@Dependent
public abstract class PMContextDetailBean<T extends BaseDataObject> {

	public abstract T initDetailObject();

	public abstract String detailPageName();

	@Inject
	private DatabaseService db;

	@Getter
	private T detailObject;

	@Getter
	private Client client;

	@Getter
	private ObjectContext context;

	@PostConstruct
	public void init() {
		context = db.newContext();
		client = DatabaseService.fetchClient(context);
		detailObject = initDetailObject();
	}

	public String actionDetail(T account) {
		if (account != null) {
			// local instance of object
			detailObject = context.localObject(account);
		} else {
			// new object
			detailObject = initDetailObject();
			// detailObject.setClient(client);
		}
		return detailPageName();
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
	}
}
