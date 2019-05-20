package org.philmaster.boot.session;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class ContextListBean<T extends BaseDataObject> implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Class<T> initClass();

	@Setter
	private List<T> items, itemsFiltered, itemsSelected;

	@Setter
	private Date date = new Date(); // Testing

	@Setter
	private T selectedItem;

	private ObjectContext context;

	@Inject
	private SessionBean session;

	@PostConstruct
	public void init() {
		context = DatabaseService.INSTANCE.newContext();

		items = DatabaseService.fetchAll(context, initClass());
	}



	public void onCreateNew() {

	}

	public void onRowSelect(SelectEvent event) {
		PMUtil.statusMessageInfo("onRowSelect", "onRowSelect");
	}

	public void onRowUnselect(UnselectEvent event) {
		PMUtil.statusMessageInfo("onRowUnselect", "onRowUnselect");

	}

}
