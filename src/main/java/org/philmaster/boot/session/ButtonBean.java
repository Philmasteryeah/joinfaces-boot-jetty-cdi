package org.philmaster.boot.session;

import java.io.Serializable;

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

	public void buttonAction(ActionEvent actionEvent) {
		Util.statusMessageInfo("Welcome", "test");
	}
}
