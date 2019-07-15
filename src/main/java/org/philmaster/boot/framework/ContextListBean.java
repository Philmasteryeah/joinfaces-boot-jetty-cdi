package org.philmaster.boot.framework;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.Setter;

public abstract class ContextListBean<T extends BaseDataObject> {

	@Inject
	private SessionBean session;

	private ObjectContext context;

	@Setter
	@Getter
	private List<T> items, itemsFiltered, itemsSelected;

	@Setter
	@Getter
	private T selectedItem;

	@Setter
	@Getter
	private Date date = new Date(); // Testing

	@PostConstruct
	public void init() {

		context = DatabaseService.getContext();
		System.err.println("init context list bean for context" + context);
		// TODO init account and client if needed
		// TODO add client in fetch
		initItems();

	}
	
	@PreDestroy
	public void dispose() {
		context.getGraphManager()
				.registeredNodes()
				.forEach(p -> System.out.println("---->" + p));

		context.rollbackChanges();
		BaseContext.bindThreadObjectContext(null);

	}

	private void initItems() {
		System.err.println("fetch all");
		try {
			items = DatabaseService.fetchAll(context, getTypeOfT());
		} catch (Exception e) {
			System.err.println(e);
			PMUtil.statusMessageError(e.getMessage());
		}
	}

	public ObjectContext getContext() {

		if (context == null)
			context = DatabaseService.getContext();
		return context;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getTypeOfT() {
		return (Class<T>) PMUtil.getParameterizedTypes(this)[0];
	}

	public void onRowSelect(SelectEvent event) {
		Object object = event.getObject();
		System.err.println(object);
		PMUtil.statusMessageInfo("onRowSelect", "onRowSelect");
	}

	public void onRowUnselect(UnselectEvent event) {
		PMUtil.statusMessageInfo("onRowUnselect", "onRowUnselect");
	}

	public void deleteSelected() {
		if (itemsSelected == null || itemsSelected.isEmpty())
			return;

		try {
			context.deleteObjects(itemsSelected);
			context.commitChanges();
		} catch (Exception e) {
			System.out.println(e);
			PMUtil.statusMessageError(e.getMessage());
			return;
		}

		initItems();
		PMUtil.statusMessageInfo("saved");

	}

}
