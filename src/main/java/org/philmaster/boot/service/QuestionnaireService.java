package org.philmaster.boot.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.philmaster.boot.model.questionnaire.QuestionnaireJS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import lombok.extern.java.Log;

/**
 * @author Philmasteryeah
 *
 */

@Log
@Named
@ApplicationScoped
public class QuestionnaireService implements Serializable {

	@Value("classpath:static/questionnaire.json")
	private transient Resource qj;

	private transient QuestionnaireJS questionnaire;

	@PostConstruct
	void init() {
		questionnaire = FileService.mapResourceToObject(qj, QuestionnaireJS.class);
		if (questionnaire == null)
			log.info("could not load questionnaire");
	}

	public QuestionnaireJS getQuestionnaire() {
		return questionnaire;
	}

}
