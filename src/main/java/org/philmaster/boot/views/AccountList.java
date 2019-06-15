package org.philmaster.boot.views;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Account;
import org.philmaster.boot.session.ContextListBean;

@Named
@RequestScoped
public class AccountList extends ContextListBean<Account> {

//	@Override
//	public Class<Account> initClass() {
//		return Account.class;
//	}

}
