package org.philmaster.boot.session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectById;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.PMUtil;

import lombok.Getter;

public abstract class ContextDetailBean<T extends BaseDataObject> implements Serializable{

//	private Class<T> persistentClass;

	private static final long serialVersionUID = 1L;

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

	public ContextDetailBean() {
		System.err.println(context.getChannel() + " init");

	

	}
	
//	@PostConstruct
//	public void init() {
////		persistentClass = getTypeOfT();
//
//		
////	
////		if (persistentClass != null)
////			detailPageName = "" + persistentClass.getSimpleName()
////					.toLowerCase() + "Detail";
//	}


	public void setDetailId(String detailId) {
		this.detailId = detailId;
		setDetailObject();
	}

	public void setDetailObject() {
		System.err.println("---" + context);
		this.detailObject = detailId != null ? fetchDetailObjectById(detailId) : createDetailObject();
	}


	@SuppressWarnings("unchecked")
	private Class<T> getTypeOfT() {
		return (Class<T>) getParameterizedTypes(this)[0];
	}


	public static Type[] getParameterizedTypes(Object object) {
		Type superclassType = object.getClass()
				.getGenericSuperclass();
//		if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
//			return null;
//		}
		return ((ParameterizedType) superclassType).getActualTypeArguments();
	}

	private T createDetailObject() {
		detailObject = context.newObject(getTypeOfT());
	
		detailObject.setToOneTarget("client", client, true);
		return detailObject;
	}

	private T fetchDetailObjectById(String detailId) {
	
		detailObject = SelectById.query(getTypeOfT(), detailId)
				.selectOne(context);
		return detailObject;
	}

	public void actionSave(ActionEvent actionEvent) {
		try {
			context.commitChanges();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			PMUtil.statusMessageError("could not save");
			return;
		}
		PMUtil.statusMessageInfo("Saved", "Saved");
	}
}
