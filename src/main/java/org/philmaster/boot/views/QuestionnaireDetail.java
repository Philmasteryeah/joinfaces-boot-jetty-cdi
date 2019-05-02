package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.model.questionnaire.QuestionJS;
import org.philmaster.boot.model.questionnaire.QuestionnaireJS;
import org.philmaster.boot.service.QuestionnaireService;
import org.philmaster.boot.session.ContextDetailBean;
import org.philmaster.boot.session.SessionBean;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

@Named
@ViewScoped
public class QuestionnaireDetail extends ContextDetailBean<Questionnaire> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionBean session;

	@Inject
	private QuestionnaireService questService;

	private transient QuestionnaireJS questionnaireJS;

	@Override
	public void init() {
		super.init();
		questionnaireJS = questService.getQuestionnaire();
	}

	@Override
	public void actionSave(ActionEvent actionEvent) {
		System.err.println(getContext().uncommittedObjects());
		// before save
		super.actionSave(actionEvent);
		// after save
		getDetailObject().setAccount(session.getLocalAccount(getContext()));
		getDetailObject().setClient(session.getLocalClient(getContext()));

		System.err.println(getContext().uncommittedObjects());

		System.err.println("quest in db name -> " + getDetailObject().getName());

	}

//	@PostConstruct
//	public void init() {
//		questionnaireJS = questService.getQuestionnaire();
//
//		context = session.getDb()
//				.newContext();
//
//		detailObject = DatabaseService.createNew(context, Questionnaire.class);
//
//		// local copy of the client from session context to this view context
//		// detailObject = session.getClient(context);
//
//	}

	public QuestionnaireJS getQuestionnaire() {
		return questionnaireJS;
	}

	public List<QuestionJS> getQuestions() {
		return questionnaireJS.getQuestion();
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
