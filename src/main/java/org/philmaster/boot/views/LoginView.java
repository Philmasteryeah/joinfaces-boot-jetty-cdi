package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.philmaster.boot.util.PMUtil;

@Named
@RequestScoped
public class LoginView {

	public void actionLogin(ActionEvent actionEvent) {
		System.err.println("login view action");
		PMUtil.statusMessageInfo("Welcome", "saved");

	}
}
