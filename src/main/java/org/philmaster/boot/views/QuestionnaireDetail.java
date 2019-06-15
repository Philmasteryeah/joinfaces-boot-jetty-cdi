package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.model.questionnaire.QuestionJS;
import org.philmaster.boot.service.QuestionnaireService;
import org.philmaster.boot.session.ContextDetailBean;

@Named
@RequestScoped
public class QuestionnaireDetail extends ContextDetailBean<Questionnaire> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuestionnaireService questService;

	private List<QuestionJS> questions;

	public List<QuestionJS> getQuestions() {
		if (questions == null)
			questions = questService.getQuestionnaireJSON()
					.getQuestion();
		return questions;
	}

	@Override
	public void actionSave() {
		System.err.println("save before");
		super.actionSave();
		System.err.println("save after");
	}

}
