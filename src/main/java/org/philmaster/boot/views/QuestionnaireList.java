package org.philmaster.boot.views;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Questionnaire;

@Named
@RequestScoped
public class QuestionnaireList extends ContextListBean<Questionnaire> {

	@PostConstruct
	@Override
	public void init() {
		// TODO
		super.init();
	}

	public void initView() {

		System.err.println("testing auth - " + getSession());

	}

}
