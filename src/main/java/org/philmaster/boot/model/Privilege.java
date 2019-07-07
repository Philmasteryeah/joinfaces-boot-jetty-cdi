package org.philmaster.boot.model;

import org.philmaster.boot.model.auto._Privilege;

public class Privilege extends _Privilege {

	private static final long serialVersionUID = 1L;

	public String id() {
		if (objectId == null) {
			System.err.println("object is transient, need to be created with context for id");
			return null;
		}
		return String.valueOf(objectId.getIdSnapshot()
				.get(_Privilege.PRIVILEGE_ID_PK_COLUMN));
	}

}
