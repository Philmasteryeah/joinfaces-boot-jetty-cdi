package org.philmaster.boot.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.philmaster.boot.model.questionnaire.Question;
import org.philmaster.boot.model.questionnaire.Questionnaire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Philmasteryeah
 *
 */

@Named
@ApplicationScoped
public class QuestionnaireService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Value("classpath:static/questionnaire.json")
	private Resource qj;

	private Questionnaire questionnaire;

	@PostConstruct
	void init() {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			questionnaire = objectMapper.readValue(qj.getInputStream(), Questionnaire.class);
		} catch (IOException e) {
			System.out.println(e.getMessage()); // TODO Logging
		}

		if (questionnaire == null)
			System.err.println("could not load questionnaire");
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

}
