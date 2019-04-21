package org.philmaster.boot.session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectById;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;

import lombok.Getter;
import lombok.Setter;

@Dependent
public abstract class ContextDetailBean<T extends BaseDataObject> {

	public abstract Class<T> initClass();

	private Class<T> persistentClass;

	@Getter
	private String detailPageName;

	@Getter
	@Setter
	private T detailObject;

	@Getter
	@Setter
	public String detailId; // f:param name="id" value="#{detailObject.id()}" from dataTable

	// page context
	@Getter
	private ObjectContext context;

	private Client client;

	@Inject
	@Getter
	private SessionBean session;

	@PostConstruct
	public void init() {
		persistentClass = initClass();
		context = DatabaseService.INSTANCE.newContext();
		BaseContext.bindThreadObjectContext(context);
		client = session.getLocalClient(context);
		detailPageName = persistentClass.getSimpleName()
				.toLowerCase() + "Detail";
	}

	public void initDetail() {
		if (detailId != null) {
			// fetch by id
			session.getAccount()
					.getClient();
			// TODO add account
			// TODO add permissions
			detailObject = SelectById.query(persistentClass, detailId)
					.selectOne(context);
		} else {
			// or create new
			detailObject = context.newObject(persistentClass);
			detailObject.setToOneTarget("client", client, true); // TODO client out here
		}
	}

	public void actionSave(ActionEvent actionEvent) {
		try {
			context.commitChanges();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Util.statusMessageError("could not save");
			return;
		}
		Util.statusMessageInfo("Saved", "Saved");
	}
}
