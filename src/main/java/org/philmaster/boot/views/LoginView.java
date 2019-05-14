package org.philmaster.boot.views;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.util.PMUtil;

@Named
@ViewScoped
public class LoginView implements Serializable {

	public void actionLogin(ActionEvent actionEvent) {
		System.err.println("login view action");
		PMUtil.statusMessageInfo("Welcome", "saved");

	}
}
