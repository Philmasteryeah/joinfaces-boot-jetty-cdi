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
    private PagerBean bean;

    public void buttonAction(ActionEvent actionEvent) {
	addMessage("Welcome to Primefaces!!");
    }

    public void addMessage(String message) {
	FacesContext context = FacesContext.getCurrentInstance();

	context.addMessage(null,
		new FacesMessage("Successful", "Your Page: " + bean.getPage() + " Your message: " + message));
    }
}
