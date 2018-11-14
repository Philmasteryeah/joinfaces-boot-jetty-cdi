package org.philmaster.boot.views;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.philmaster.boot.model.Account;
import org.philmaster.boot.session.PMContextDetailBean;

/**
 * @author Philmaster
 *
 *         Needs to be session scoped because it gets the detail object from a
 *         list page.
 *
 */
@Named
@SessionScoped
public class CarDetail extends PMContextDetailBean<Account> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Account initDetailObject() {
		return getContext().newObject(Account.class);
	}

	@Override
	public String detailPageName() {
		return "carDetail";
	}

	public void actionAdd(ActionEvent actionEvent) {
//		for (int i = 0; i < 10; i++) {
//			Car.createRandomTestCar(context, client);
//		}		
//		//context.commitChanges();
//		Util.statusMessageInfo("Welcome", "saved");

	}

}
