package org.philmaster.boot.session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;

import lombok.Getter;
import lombok.Setter;

@Dependent
public abstract class ContextDetailBean<T extends BaseDataObject> {

	public abstract Class<T> initClass();

	private Class<T> persistentClass;

	private String detailPageName;

	@Getter
	@Setter
	private T detailObject;

	@Getter
	private ObjectContext context;

	@Getter
	private Client client;

	@Inject
	private DatabaseService db;

	@PostConstruct
	public void init() {
		persistentClass = initClass();
		context = db.newContext();
		client = DatabaseService.fetchClient(context);
		detailPageName = persistentClass.getSimpleName().toLowerCase() + "Detail?faces-redirect=true";
	}

//	public void initDetail() {
//		System.err.println("initDetail");
//		if (detailObject != null) {
//			// local instance of object
//			detailObject = context.localObject(detailObject);
//		} else {
//			// new object
//			detailObject = context.newObject(persistentClass);
//			// detailObject.setToOneTarget("client", client, true);
//			// detailObject.setClient(client);
//		}
//	}

	public String actionDetail(T detail) {
		System.err.println("asd -> " + detail);
		if (detail != null) {
			// local instance of object
			detailObject = context.localObject(detail);
		} else {
			// new object
			context.newObject(persistentClass);
			// detailObject.setToOneTarget("client", client, true);
			// detailObject.setClient(client);
		}
		return detailPageName;
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();
		Util.statusMessageError("Validation Exception", "Test");
	}
}
