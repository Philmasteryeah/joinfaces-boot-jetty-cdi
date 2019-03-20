package org.philmaster.boot.model;

import org.philmaster.boot.model.auto._Questionnaire;

public class Questionnaire extends _Questionnaire {

	private static final long serialVersionUID = 1L;

	public Object id() {
		// <ObjectId:Questionnaire, questionnaire_id=1>
		return objectId.getIdSnapshot()
				.get("questionnaire_id");
	}
}
