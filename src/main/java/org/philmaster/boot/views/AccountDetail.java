package org.philmaster.boot.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Philmaster
 *
 *         Needs to be session scoped because it gets the detail object from a
 *         list page.
 *
 */
@Named
@SessionScoped
public class AccountDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private Account detailObject;

	@Getter
	private Client client;

	@Inject
	private DatabaseService db;

	private ObjectContext context;

	@PostConstruct
	public void init() {
		context = db.newContext();
		client = DatabaseService.clientByName(context);
	}

	public String actionDetail(Account account) {
		if (account != null) {
			// local instance of object
			detailObject = context.localObject(account);
		} else {
			// new object
			detailObject = context.newObject(Account.class);
			detailObject.setClient(client);
		}
		return "accountDetail";
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
	}

	public void actionAdd(ActionEvent actionEvent) {

//		for (int i = 0; i < 10; i++) {
//			Account.createRandomTestAccount(context, client);
//		}		
//		
//		
//		//context.commitChanges();
//		Util.statusMessageInfo("Welcome", "saved");

	}

}
