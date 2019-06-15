package org.philmaster.boot.views;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.session.ContextListBean;

@Named
@RequestScoped
public class QuestionnaireList extends ContextListBean<Questionnaire> implements Serializable {

	private static final long serialVersionUID = 1L;

}
