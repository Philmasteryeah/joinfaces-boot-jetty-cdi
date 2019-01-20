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

			@Override
			public String toString() {
				return "ClassPojo [title = " + title + ", key = " + key + "]";
			}
		}

		@Override
		public String toString() {
			return "ClassPojo [rank = " + rank + ", title = " + title + ", isMandatory = " + isMandatory + ", answer = "
					+ answer + ", attribute = " + attribute + "]";
		}
	}

	@Override
	public String toString() {
		return "ClassPojo [name = " + name + ", question = " + question + "]";
	}
}
