package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Role;

@Named
@RequestScoped
public class RoleList extends ContextListBean<Role> {

//	@Override
//	public void init() {
//		super.init();
//
//	}

}
