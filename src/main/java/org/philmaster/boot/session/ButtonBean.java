package org.philmaster.boot.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.philmaster.boot.util.Util;

/**
 * @author Philmasteryeah
 * 
 */

@Named
@SessionScoped
public class ButtonBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionBean session;

	public void buttonAction(ActionEvent actionEvent) {
		System.err.println(actionEvent + " ");
		Util.statusMessageInfo("Welcome", "test");
	}

	public void buttonTest(ActionEvent actionEvent) throws Exception {
		System.err.println(actionEvent + " ");
		Util.statusMessageInfo("Welcome", "test");

		// Testing stuff here
		// meals.forEach(System.err::println);
	}

	public void doAction() {
		Messages.create("Welcome to AdminBoot " + session.getUsername() + "!")
				.detail("<b>AdminFaces</b> and <b>SpringBoot</b> integration via <b>JoinFaces.</b>").add();
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
