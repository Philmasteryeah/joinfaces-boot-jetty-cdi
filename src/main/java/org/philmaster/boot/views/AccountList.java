package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.cayenne.exp.ExpressionFactory;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.PMContextListBean;

@Named
@ViewScoped
public class AccountList extends PMContextListBean<Account> implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Account> initList() {
		//
		// return getClient().getAccounts();Caused by: javax.el.ELException: Cannot
		// convert [org.apache.cayenne.access.ToManyList@114808156] of type [class
		// org.apache.cayenne.access.ToManyList] to [class java.util.ArrayList]
		//
		return DatabaseService.fetch(getContext(), Account.class, ExpressionFactory.matchExp(getClient()),
				Account.USERNAME.ascInsensitive());
	}

}
