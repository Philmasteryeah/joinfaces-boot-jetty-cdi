package org.philmaster.boot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Questionnaire {

	private String name;
	private Question[] question;

	@Getter
	@Setter
	public class Question {

		private String rank;
		private String title;
		private String isMandatory;
		private Answer[] answer;
		private String attribute;

		@Getter
		@Setter
		public class Answer {

			private String title;
			private String key;

		}

	}

}
