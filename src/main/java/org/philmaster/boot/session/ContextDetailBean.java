package org.philmaster.boot.session;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectById;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;

import lombok.Getter;

@Dependent
public abstract class ContextDetailBean<T extends BaseDataObject> {

	private Class<T> persistentClass;

	@Getter
	private String detailPageName;

	@Getter
	private T detailObject;

	@Getter
	private String detailId; // f:param name="id" value="#{detailObject.id()}" from dataTable

	@Getter
	private final ObjectContext context = DatabaseService.INSTANCE.newContext();

	private Client client;

	@Inject
	@Getter
	private SessionBean session;

	@PostConstruct
	public void init() {
		persistentClass = getTypeOfT();
		
		System.err.println(context.getChannel() + " init");
		
		client = session.getLocalClient(context);

		// TODO not needed can be pure el
		if (persistentClass != null)
			detailPageName = "" + persistentClass.getSimpleName()
					.toLowerCase() + "Detail";
	}

	// will set on page navigation
	public void setDetailId(String detailId) {
		this.detailId = detailId;
		setDetailObject();
	}

	public void setDetailObject() {
		this.detailObject = detailId != null ? fetchDetailObjectById(detailId) : createDetailObject();
	}

	// evil but I like it
	@SuppressWarnings("unchecked")
	private Class<T> getTypeOfT() {
		return (Class<T>) getParameterizedTypes(this)[0];
	}

	// UTIL
	public static Type[] getParameterizedTypes(Object object) {
		Type superclassType = object.getClass()
				.getGenericSuperclass();
//		if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
//			return null;
//		}
		return ((ParameterizedType) superclassType).getActualTypeArguments();
	}

	private T createDetailObject() {
		detailObject = context.newObject(persistentClass);
		// every detail object has a client
		detailObject.setToOneTarget("client", client, true);
		return detailObject;
	}

	private T fetchDetailObjectById(String detailId) {
		// TODO add account
		detailObject = SelectById.query(persistentClass, detailId)
				.selectOne(context);
		return detailObject;
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
