package org.philmaster.boot.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.philmaster.boot.model.questionnaire.QuestionnaireJSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.extern.java.Log;

/**
 * @author Philmasteryeah
 *
 */

@Log
@Named
@ApplicationScoped
public class QuestionnaireService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Value("classpath:static/questionnaire.json")
	private transient Resource qj;

	@Getter
	private transient QuestionnaireJSON questionnaireJSON;

	@PostConstruct
	void init() {
		questionnaireJSON = FileService.mapResourceToObject(qj, QuestionnaireJSON.class);
		if (questionnaireJSON == null)
			log.info("could not load questionnaire");
	}

}
