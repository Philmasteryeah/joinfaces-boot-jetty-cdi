package org.philmaster.boot.views;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.philmaster.boot.model.Account;
import org.philmaster.boot.session.ContextDetailBean;

@Named
@SessionScoped
public class AccountDetail extends ContextDetailBean<Account> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Account> initClass() {
		return Account.class;
	}

	@Override
	public void actionSave(ActionEvent actionEvent) {
		getDetailObject().setClient(getClient());
		super.actionSave(actionEvent);
	}

}
