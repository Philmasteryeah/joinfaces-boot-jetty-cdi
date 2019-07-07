package org.philmaster.boot.model;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.auto._Role;
import org.philmaster.boot.service.DatabaseService;

public class Role extends _Role {

	private static final long serialVersionUID = 1L;

//	private List<Privilege> privileges;
//
//	public List<Privilege> getPrivileges() {
//		return getAccesses().stream()
//				.map(Access::getPrivilege)
//				.collect(Collectors.toList());
//	}
//
//	public void setPrivileges(List<Privilege> privileges, ObjectContext context) {
//		privileges.stream()
//				.filter(p -> !getPrivileges().contains(p)) // filter out all already attached privilges
//				.forEach(p -> {
//					Access access = DatabaseService.createNew(context, Access.class);
//					access.setPrivilege(p);
//					access.setRole(this);
//				});
//	}

}
