package org.philmaster.boot.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
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

	public void buttonAction(ActionEvent actionEvent) {
		addMessage("Welcome to Primefaces!!");
	}

	private void addMessage(String message) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		String user = ctx.getExternalContext().getRemoteUser();
		String page = session.getPagePrettyPrinted();
		ctx.addMessage(null, new FacesMessage("Successful", "Welcome " + user + " on page: " + page + " : " + message));
	}
}
