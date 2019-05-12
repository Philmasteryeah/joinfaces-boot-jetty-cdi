package org.philmaster.boot.service;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.philmaster.boot.model.questionnaire.QuestionnaireJS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	private transient QuestionnaireJS questionnaire;

	@PostConstruct
	void init() {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			questionnaire = objectMapper.readValue(qj.getInputStream(), QuestionnaireJS.class);
		} catch (IOException e) {
			log.warning(e.getMessage());
		}

		if (questionnaire == null)
			System.err.println("could not load questionnaire");
	}

	public QuestionnaireJS getQuestionnaire() {
		return questionnaire;
	}

}
