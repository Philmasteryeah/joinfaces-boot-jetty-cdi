package org.philmaster.boot.framework;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

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

	private SessionBean session;

	@Setter
	@Getter
	private List<T> items, itemsFiltered, itemsSelected;

	@Setter
	@Getter
	private T selectedItem;

	@Setter
	@Getter
	private Date date = new Date(); // Testing

	private ObjectContext context;

	@PostConstruct
	public void init() {
		context = getContext();
		session = getSession();

		// TODO copy account in local context if needed

		items = DatabaseService.fetchAll(context, getTypeOfT());

	}

	public ObjectContext getContext() {
		if (context == null)
			context = DatabaseService.getContext();
		return context;
	}

	private SessionBean getSession() {
		if (session == null) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			session = ctx.getApplication()
					.evaluateExpressionGet(ctx, "#{sessionBean}", SessionBean.class);
		}
		return session;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getTypeOfT() {
		return (Class<T>) PMUtil.getParameterizedTypes(this)[0];
	}

	public void onRowSelect(SelectEvent event) {
		PMUtil.statusMessageInfo("onRowSelect", "onRowSelect");
	}

	public void onRowUnselect(UnselectEvent event) {
		PMUtil.statusMessageInfo("onRowUnselect", "onRowUnselect");

	}

}
