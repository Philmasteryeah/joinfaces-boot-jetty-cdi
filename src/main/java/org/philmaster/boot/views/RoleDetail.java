package org.philmaster.boot.views;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectById;
import org.flywaydb.core.internal.database.base.Database;
import org.philmaster.boot.model.Access;
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

	@PostConstruct
	public void init() {
		context = getContext();
		initSession();
		initDetailObject(context);
		initPrivileges();
	}

	private void initSession() {
		client = session.getLocalClient(context);
		account = session.getLocalAccount(context);
	}

	public void initDetailObject(ObjectContext ctx) {
		initDetailObject(ctx, getRequestId());
	}

	public void initDetailObject(ObjectContext ctx, String id) {
		detailObject = (id != null) ? fetchDetailObjectById(ctx, id) : createDetailObject(ctx);
	}

	private void initPrivileges() {
		List<Privilege> privilegesSource = DatabaseService.fetchAll(context, Privilege.class);
		List<Privilege> privilegesTarget = detailObject.getPrivileges();
		privilegesSource.removeAll(privilegesTarget); // remove all already assigned privileges from source
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

	private Role createDetailObject(ObjectContext ctx) {
		return ctx.newObject(Role.class);
	}

	private Role fetchDetailObjectById(ObjectContext context, String detailId) {
		return SelectById.query(Role.class, detailId)
				.selectOne(context);
	}

	public void actionSave() {
		List<Privilege> privilegesAssigned = detailObject.getPrivileges();
		List<Privilege> source = privileges.getSource();
		List<Privilege> target = privileges.getTarget();

		// delete only old assigned privileges who are now not assigned
		source.stream()
				.filter(privilegesAssigned::contains)
				.forEach(detailObject::removeFromPrivileges);
		// add only new not assigned privileges
		target.stream()
				.filter(p -> !privilegesAssigned.contains(p))
				.forEach(detailObject::addToPrivileges);

		detailObject.setClient(client);

		try {
			context.commitChanges();
		} catch (Exception e) {
			PMUtil.statusMessageError(e.getMessage());
			return;
		}
		PMUtil.statusMessageInfo("Saved", "Saved");
	}
///////////////

}
