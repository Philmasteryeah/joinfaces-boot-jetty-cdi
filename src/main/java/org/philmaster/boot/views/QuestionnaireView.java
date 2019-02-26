package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.questionnaire.Question;
import org.philmaster.boot.model.questionnaire.Questionnaire;
import org.philmaster.boot.service.QuestionnaireService;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class QuestionnaireView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuestionnaireService questService;

	private Questionnaire questionnaire;

	@Getter
	@Setter
	private String question1;

	@PostConstruct
	public void init() {
		questionnaire = questService.getQuestionnaire();
		if (questionnaire == null) {
			// TODO statusmessage error
		}
		
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public List<Question> getQuestions() {
		return questionnaire.getQuestion();
	}

	///////////////

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
