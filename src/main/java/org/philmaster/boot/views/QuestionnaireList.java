package org.philmaster.boot.views;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.session.ContextListBean;

@Named
@ViewScoped
public class QuestionnaireList extends ContextListBean<Questionnaire> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Questionnaire> initClass() {
		return Questionnaire.class;
	}

}
