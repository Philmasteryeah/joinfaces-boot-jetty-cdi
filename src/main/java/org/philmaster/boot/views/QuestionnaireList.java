package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Questionnaire;

@Named
@RequestScoped
public class QuestionnaireList extends ContextListBean<Questionnaire> {


	public void initView() {
		System.err.println("init quest list called from xhtml <f:viewAction action=\"#{questionnaireList.initView}\" />");


	}

}
