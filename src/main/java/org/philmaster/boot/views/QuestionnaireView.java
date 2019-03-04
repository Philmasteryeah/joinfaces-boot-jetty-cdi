package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.model.questionnaire.QuestionJS;
import org.philmaster.boot.model.questionnaire.QuestionnaireJS;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.service.QuestionnaireService;
import org.philmaster.boot.session.SessionBean;
import org.philmaster.boot.util.Util;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class QuestionnaireView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionBean session;

	@Inject
	private QuestionnaireService questService;

	private QuestionnaireJS questionnaire;

	@Getter
	@Setter
	private String question1; // testing

	private ObjectContext context;

	@Getter
	private Questionnaire detailObject;

	@PostConstruct
	public void init() {
		questionnaire = questService.getQuestionnaire();
		if (questionnaire == null) {
			// TODO statusmessage error
		}

		context = session.getDb()
				.newContext();

		detailObject = DatabaseService.createNew(context, Questionnaire.class);

		// local copy of the client from session context to this view context
		// detailObject = session.getClient(context);

	}

	public QuestionnaireJS getQuestionnaire() {
		return questionnaire;
	}

	public List<QuestionJS> getQuestions() {
		return questionnaire.getQuestion();
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();

		Util.statusMessageInfo("Saved", "Saved");
		// TODO
		// detailObject.setAccount(account);

		System.err.println("quest in db name -> " + detailObject.getName());

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
