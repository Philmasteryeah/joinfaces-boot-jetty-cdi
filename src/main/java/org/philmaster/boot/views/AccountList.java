package org.philmaster.boot.views;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Account;
import org.philmaster.boot.session.ContextListBean;

@Named
@ViewScoped
public class AccountList extends ContextListBean<Account> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Account> initClass() {
		return Account.class;
	}
}
