package org.philmaster.boot.views;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextDetailBean;
import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.model.questionnaire.QuestionJS;
import org.philmaster.boot.service.QuestionnaireService;

@Named
@RequestScoped
public class QuestionnaireDetail extends ContextDetailBean<Questionnaire> {

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
		getDetailObject().setAccount(getAccount());
		System.err.println(getClient());
		System.err.println("save before");
		super.actionSave();
		System.err.println("save after");
	}

}
