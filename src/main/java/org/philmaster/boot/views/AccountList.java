package org.philmaster.boot.views;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Account;

@Named
@RequestScoped
public class AccountList extends ContextListBean<Account> {

	@Override
	@PostConstruct
	public void init() {
		super.init();
		// do your init stuff
		System.err.println("-> " + getItems());
		
	}

}
