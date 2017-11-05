package org.philmaster.boot.beans;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Philmasteryeah
 * 
 *         testing stuff
 *
 */

@Named
@RequestScoped
public class ButtonBean {

	@Inject
	private SessionBean session;

	@Inject
	private FacesContext context;

	public void buttonAction(ActionEvent actionEvent) {
		addMessage("Welcome to Primefaces!!");
	}

	private void addMessage(String message) {
		String user = context.getExternalContext().getRemoteUser();
		String page = session.getPagePrettyPrinted();
		context.addMessage(null, new FacesMessage("Successful", "Welcome " + page + " on page: test : " + message));
	}

}
