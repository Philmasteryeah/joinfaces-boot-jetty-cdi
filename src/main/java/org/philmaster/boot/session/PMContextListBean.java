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
public abstract class PMContextListBean<T extends BaseDataObject> {

	public abstract List<T> initItems();

	// ---------

	@Inject
	private DatabaseService db;

	@Getter
	private Client client;

	@Getter
	private List<T> items;

	@Getter
	@Setter
	private T selectedItem;

	@Getter
	private ObjectContext context;

	@PostConstruct
	public void init() {
		context = db.newContext();
		client = DatabaseService.clientByName(context);
		items = initItems();
	}

	public void onRowSelect(SelectEvent event) {
		Util.statusMessageInfo("Selected", "'" + selectedItem + "'");
	}
}
