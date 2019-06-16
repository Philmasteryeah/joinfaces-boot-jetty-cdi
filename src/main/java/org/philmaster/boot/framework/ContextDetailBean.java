package org.philmaster.boot.framework;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectById;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import lombok.Getter;

public abstract class ContextDetailBean<T extends BaseDataObject> {

	private SessionBean session;

	private ObjectContext context;

	@Getter
	private String detailPageName;

	@Getter
	private T detailObject;
	@Getter
	private Client client;
	@Getter
	private Account account;

	@PostConstruct
	public void init() {
		context = getContext();
		initSession();
		initDetailObject(context);
		
	}

	public ObjectContext getContext() {
		if (context == null)
			context = DatabaseService.getContext();
		return context;
	}

	private String getRequestId() {
		return FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap()
				.get("id");
	}

	private SessionBean getSession() {
		if (session == null) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			session = ctx.getApplication()
					.evaluateExpressionGet(ctx, "#{sessionBean}", SessionBean.class);
		}
		return session;
	}

	public void initDetailObject(ObjectContext ctx) {
		initDetailObject(ctx, getRequestId());
	}

	public void initDetailObject(ObjectContext ctx, String id) {
		detailObject = (id != null) ? fetchDetailObjectById(ctx, id) : createDetailObject(ctx);
		detailObject.setToOneTarget("client", client, true);
	}

	private void initSession() {
		session = getSession();
		client = session.getLocalClient(context);
		account = session.getLocalAccount(context);
	}

	private T createDetailObject(ObjectContext ctx) {
		return ctx.newObject(getTypeOfT());
	}

	private T fetchDetailObjectById(ObjectContext context, String detailId) {
		return SelectById.query(getTypeOfT(), detailId)
				.selectOne(context);
	}

	public void actionSave() {
		try {
			getContext().commitChanges();
		} catch (Exception e) {
			PMUtil.statusMessageError(e.getMessage());
			return;
		}
		PMUtil.statusMessageInfo("Saved", "Saved");
	}

	public void onTabChange(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab()
				.getTitle());
		FacesContext.getCurrentInstance()
				.addMessage(null, msg);
	}

	public void onTabClose(TabCloseEvent event) {
		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab()
				.getTitle());
		FacesContext.getCurrentInstance()
				.addMessage(null, msg);
	}

	@SuppressWarnings("unchecked")
	private Class<T> getTypeOfT() {
		return (Class<T>) PMUtil.getParameterizedTypes(this)[0];
	}

}
