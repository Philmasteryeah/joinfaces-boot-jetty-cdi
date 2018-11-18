package org.philmaster.boot.session;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;
import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;

@Dependent
public abstract class ContextListBean<T extends BaseDataObject> {

	public abstract Class<T> initClass();

	private Class<T> persistentClass;

	@Getter
	@Setter
	private List<T> items, itemsFiltered;
	
	@Getter
	@Setter
	private T selectedItem;

	@Getter
	private Client client;

	@Getter
	private ObjectContext context;

	@Inject
	private DatabaseService db;

	@PostConstruct
	public void init() {
		persistentClass = initClass();
		context = db.newContext();
		client = DatabaseService.fetchClient(context);
		items = DatabaseService.fetchAll(context, persistentClass);
	}

	public void onRowSelect(SelectEvent event) {
		// TODO
		Util.statusMessageInfo("Selected", "'" + selectedItem + "'");
	}

}
