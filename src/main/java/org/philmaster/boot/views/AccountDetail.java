package org.philmaster.boot.views;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Account;
import org.philmaster.boot.session.ContextDetailBean;

@Named
@ViewScoped
public class AccountDetail extends ContextDetailBean<Account> implements Serializable {

	private static final long serialVersionUID = 1L;

}
