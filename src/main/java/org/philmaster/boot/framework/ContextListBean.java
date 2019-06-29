package org.philmaster.boot.framework;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.Setter;

@Dependent
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
		context = getContext();

		// TODO init account and client if needed
		// TODO add client in fetch
		initItems();

	}

	private void initItems() {
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

	public String deleteSelected() {
		if (itemsSelected == null || itemsSelected.isEmpty())
			return null;
		context.deleteObjects(itemsSelected);
		context.commitChanges();
		initItems();
		return null;
	}

}
