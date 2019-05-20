package org.philmaster.boot.views;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.model.questionnaire.QuestionJS;
import org.philmaster.boot.model.questionnaire.QuestionnaireJSON;
import org.philmaster.boot.service.QuestionnaireService;
import org.philmaster.boot.session.ContextDetailBean;
import org.philmaster.boot.session.SessionBean;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

@Named
@ViewScoped
public class QuestionnaireDetail extends ContextDetailBean<Questionnaire> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionBean session;

	@Inject
	private QuestionnaireService questService;


	private transient QuestionnaireJSON questionnaireJS;

//	@Override
//	public void init() {
//		super.init();
//		questionnaireJS = 
//	}

	public QuestionnaireDetail() {
		super();
		System.err.println("construct quest detail bean");

	}

	@Override
	public void actionSave(ActionEvent actionEvent) {
		System.err.println(getContext().uncommittedObjects());
	
		super.actionSave(actionEvent);
	
		getDetailObject().setAccount(session.getAccount());
		getDetailObject().setClient(session.getClient());

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
//	
//	
//
//	}

	public QuestionnaireJSON getQuestionnaire() {
		if (questionnaireJS == null)
			questionnaireJS = questService.getQuestionnaireJSON();
		return questionnaireJS;
	}

	public List<QuestionJS> getQuestions() {
		return getQuestionnaire().getQuestion();
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
