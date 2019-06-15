package org.philmaster.boot.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.philmaster.boot.model.questionnaire.QuestionnaireJSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

/**
 * @author Philmasteryeah
 *
 */

@Named
@ApplicationScoped
public class QuestionnaireService {

	@Value("classpath:static/questionnaire.json")
	private Resource qj;

	private QuestionnaireJSON questionnaireJSON;

	@PostConstruct
	void init() {
		questionnaireJSON = FileService.mapResourceToObject(qj, QuestionnaireJSON.class);
	}

	public QuestionnaireJSON getQuestionnaireJSON() {
		return questionnaireJSON;
	}

}
