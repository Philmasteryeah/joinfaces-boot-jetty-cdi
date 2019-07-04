package org.philmaster.boot.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectById;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.model.Privilege;
import org.philmaster.boot.model.Role;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.model.DualListModel;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class RoleDetail {

	@Inject
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

	@Getter
	@Setter
	private DualListModel<Privilege> privileges;

	@Getter
	private List<Privilege> privilegesSource, privilegesTarget;

	@PostConstruct
	public void init() {
		context = getContext();
		initSession();
		initDetailObject(context);
		
		privilegesSource = new ArrayList<>();
		privilegesTarget = new ArrayList<>();

		Privilege privilege = new Privilege();
		privilege.setName("Read");
		privilegesSource.add(privilege);

		privileges = new DualListModel<>(privilegesSource, privilegesTarget);

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

	public void initDetailObject(ObjectContext ctx) {
		initDetailObject(ctx, getRequestId());
	}

	public void initDetailObject(ObjectContext ctx, String id) {
		detailObject = (id != null) ? fetchDetailObjectById(ctx, id) : createDetailObject(ctx);

	}

	private void initSession() {
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
		detailObject.setClient(client);
		try {
			getContext().commitChanges();
		} catch (Exception e) {
			PMUtil.statusMessageError(e.getMessage());
			return;
		}
		PMUtil.statusMessageInfo("Saved", "Saved");
	}
///////////////

}
