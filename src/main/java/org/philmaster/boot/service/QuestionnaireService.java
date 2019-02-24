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

	private Questionnaire questionnaire;

	@Value("classpath:static/questionnaire.json")
	private Resource qj;

	@PostConstruct
	void init() {

		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			questionnaire = objectMapper.readerFor(Questionnaire.class)
					.readValue(qj.getInputStream());
			System.err.println(questionnaire.toString()+"asd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("--->" + questionnaire.getAttributes());
		if (questionnaire == null) {
			System.err.println("err");
			return;
		}
	}

	public String getFirstTitle() {
		List<Question> questions = questionnaire.getQuestion();

		Question question = questions.get(0);
		String title = question.getAttributes()
				.getTitle();
		System.err.println(title + "------------");
		return title;

	}

}
