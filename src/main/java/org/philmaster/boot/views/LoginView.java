package org.philmaster.boot.views;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.util.Util;

@Named
@ViewScoped
public class LoginView implements Serializable {

	private static final long serialVersionUID = 1L;

	public void actionLogin(ActionEvent actionEvent) {

		System.err.println("er");
		Util.statusMessageInfo("Welcome", "saved");

	}
}
