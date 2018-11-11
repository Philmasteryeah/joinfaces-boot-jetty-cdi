package org.philmaster.boot.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

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

	public void buttonTest(ActionEvent actionEvent) {
		System.err.println(actionEvent + " ");
		Util.statusMessageInfo("Welcome", "test");

		// Testing stuff here
		// meals.forEach(System.err::println);
	}

	public void doAction() {
		Util.statusMessageInfo("Welcome", "'" + session.getUsername() + "'");
//		Messages.create("Welcome to AdminBoot " + session.getUsername() + "!")
//				.detail("<b>AdminFaces</b> and <b>SpringBoot</b> integration via <b>JoinFaces.</b>").add();

	}

	public void doAction2() {
		Util.statusMessageWarn("Welcome", "'" + session.getUsername() + "'");

	}

	public void doAction3() {
		Util.statusMessageError("Welcome", "'" + session.getUsername() + "'");

	}

	public void addMessage(String summary) {
		Util.statusMessageError("add", "'" + summary + "'");
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
//		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
