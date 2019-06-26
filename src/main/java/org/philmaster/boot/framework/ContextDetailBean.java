package org.philmaster.boot.framework;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.cayenne.BaseContext;
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

@Dependent
public abstract class ContextDetailBean<T extends BaseDataObject> {

	@Inject
	private SessionBean session;

	private ObjectContext context;

	@Getter
	private T detailObject;

	@Getter
	private Client client;

	@Getter
	private Account account;

	@Getter
	private String detailPageName;

	@PostConstruct
	public void init() {
		context = getContext();
		initClient();
		initAccount();
		initDetailObject();
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

	public void initDetailObject() {
		initDetailObject(context, getRequestId());
	}

	public void initDetailObject(ObjectContext ctx, String id) {
		detailObject = (id != null) ? fetchDetailObjectById(ctx, id) : createDetailObject(ctx);
		detailObject.setToOneTarget("client", client, true);
	}

	public void initClient() {
		client = session.getLocalClient(context);
	}

	public void initAccount() {
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
			context.commitChanges();
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
