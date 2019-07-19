package org.philmaster.boot.views;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextDetailBean;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Role;
import org.philmaster.boot.service.DatabaseService;
import org.primefaces.model.DualListModel;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class AccountDetail extends ContextDetailBean<Account> {

	@Getter
	@Setter
	private DualListModel<Role> roles;

	@Override
	public void init() {
		super.init();
		initRoles();
	}

	private void initRoles() {
		List<Role> rolesSource = DatabaseService.fetchAll(getContext(), Role.class);
		List<Role> rolesTarget = getDetailObject().getRoles();
		rolesSource.removeAll(rolesTarget); // remove all already roles
		roles = new DualListModel<>(rolesSource, rolesTarget);
	}

	@Override
	public void actionSave() {
		Account detailObject = getDetailObject();
		detailObject.setEnabled(true);
		detailObject.setClient(getClient());
		detailObject.setToManyTarget(Account.ROLES.getName(), roles.getTarget(), true);
		super.actionSave();

	}
}
