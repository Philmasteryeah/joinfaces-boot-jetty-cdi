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
import org.philmaster.boot.session.ContextDetailBean;
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

	private QuestionnaireJS questionnaireJS;

	private ObjectContext context;

	@Getter
	private Questionnaire detailObject;

	@PostConstruct
	public void init() {
		questionnaireJS = questService.getQuestionnaire();
		if (questionnaireJS == null) {
			// TODO statusmessage error cant load template
		}

		context = session.getDb()
				.newContext();

		detailObject = DatabaseService.createNew(context, Questionnaire.class);

		// local copy of the client from session context to this view context
		// detailObject = session.getClient(context);

	}

	public QuestionnaireJS getQuestionnaire() {
		return questionnaireJS;
	}

	public List<QuestionJS> getQuestions() {
		return questionnaireJS.getQuestion();
	}

	public void actionSave(ActionEvent actionEvent) {
		detailObject.setAccount(session.getAccount(context));
		detailObject.setClient(session.getClient(context));

		context.commitChanges();
		Util.statusMessageInfo("Saved", "Saved");

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
