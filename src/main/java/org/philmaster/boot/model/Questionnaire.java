package org.philmaster.boot.model;

import org.philmaster.boot.model.auto._Questionnaire;

public class Questionnaire extends _Questionnaire {

	private static final long serialVersionUID = 1L;

	public Object id() {
	
		return objectId.getIdSnapshot()
				.get("questionnaire_id");
	}
}
