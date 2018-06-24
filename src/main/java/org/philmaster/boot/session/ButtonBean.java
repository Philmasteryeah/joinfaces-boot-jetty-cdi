package org.philmaster.boot.session;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.philmaster.boot.util.Util;

/**
 * @author Philmasteryeah
 * 
 *         testing stuff
 *
 */

@Named
@SessionScoped
public class ButtonBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// ##########Test############

	@PostConstruct
	public void init() {
	}

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

}
