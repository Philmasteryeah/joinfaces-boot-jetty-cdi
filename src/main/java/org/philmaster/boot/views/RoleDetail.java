package org.philmaster.boot.views;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextDetailBean;
import org.philmaster.boot.model.Privilege;
import org.philmaster.boot.model.Role;
import org.philmaster.boot.service.DatabaseService;
import org.primefaces.model.DualListModel;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class RoleDetail extends ContextDetailBean<Role> {

	@Getter
	@Setter
	private DualListModel<Privilege> privileges;

	@Override
	public void init() {
		super.init();
		initPrivileges();
	}

	private void initPrivileges() {
		List<Privilege> privilegesSource = DatabaseService.fetchAll(getContext(), Privilege.class);
		List<Privilege> privilegesTarget = getDetailObject().getPrivileges();
		privilegesSource.removeAll(privilegesTarget); // remove all already assigned privileges from source
		privileges = new DualListModel<>(privilegesSource, privilegesTarget);
	}

	@Override
	public void actionSave() {
		getDetailObject().setClient(getClient());
		getDetailObject().setToManyTarget(Role.PRIVILEGES.getName(), privileges.getTarget(), true);
		super.actionSave();
	}

}
