package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Questionnaire;

@Named
@RequestScoped
public class QuestionnaireList extends ContextListBean<Questionnaire> {

}
