package org.philmaster.boot.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Questionnaire;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class QuestionnaireView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Questionnaire questionnaire;

	@Getter
	@Setter
	private String question1;

	@PostConstruct
	public void init() {

	}

	public void onTabChange(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab()
				.getTitle());
		FacesContext.getCurrentInstance()
				.addMessage(null, msg);
	}

	public void onTabClose(TabCloseEvent event) {
		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab()
				.getTitle());
		FacesContext.getCurrentInstance()
				.addMessage(null, msg);
	}

}
