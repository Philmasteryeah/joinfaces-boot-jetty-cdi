package org.philmaster.boot.views;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectById;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.model.Role;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.PMUtil;

import lombok.Getter;

@Named
@RequestScoped
public class RoleDetail {

	private SessionBean session;

	private ObjectContext context;

	@Getter
	private String detailPageName;

	@Getter
	private Role detailObject;
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

	}

	private void initSession() {
		session = getSession();
		client = session.getLocalClient(context);
		account = session.getLocalAccount(context);
	}

	private Role createDetailObject(ObjectContext ctx) {
		return ctx.newObject(Role.class);
	}

	private Role fetchDetailObjectById(ObjectContext context, String detailId) {
		return SelectById.query(Role.class, detailId)
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

}
