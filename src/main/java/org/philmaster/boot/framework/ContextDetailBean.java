package org.philmaster.boot.framework;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
		context = DatabaseService.getContext();
		System.err.println("init context detail bean for context" + context);
		initClient();
		initAccount();
		initDetailObject();
	}

	@PreDestroy
	public void dispose() {
		context.getGraphManager()
				.registeredNodes()
				.forEach(p -> System.out.println("---->" + p));

		context.rollbackChanges();
		BaseContext.bindThreadObjectContext(null);

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
		if (detailObject == null) {
			System.err.println("detail not found but requested");
			return;
		}
		if (client == null) {
			System.err.println("client not initialized");
			return;
		}
		// detailObject.setToOneTarget("client", client, true);
	}

//	public Client getClient() {
//		System.err.println("get client "+client);
//		if (client == null)
//			initClient();
//		return client;
//	}
//
//	public Account getAccount() {
//		System.err.println("get acc "+account);
//		if (account == null)
//			initAccount();
//		return account;
//	}

	public void initClient() {
		client = session.getLocalClient(context);
		System.err.println("init client " + client);
	}

	public void initAccount() {
		account = session.getLocalAccount(context);
		System.err.println("init account " + account);
	}

	private T createDetailObject(ObjectContext context) {
		System.err.println("create detail object for context" + context);
		return context.newObject(getTypeOfT());
	}

	private T fetchDetailObjectById(ObjectContext context, String detailId) {
		System.err.println("fetcg detail object for context" + context);
		return SelectById.query(getTypeOfT(), detailId)
				.selectOne(context);
	}

	public void actionSave() {

		System.err.println("save detail context " + context);
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
