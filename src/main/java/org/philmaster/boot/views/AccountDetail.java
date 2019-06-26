package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextDetailBean;
import org.philmaster.boot.model.Account;

@Named
@RequestScoped
public class AccountDetail extends ContextDetailBean<Account> {

	@Override
	public void init() {
		super.init();
		// do your init stuff

	}

}
