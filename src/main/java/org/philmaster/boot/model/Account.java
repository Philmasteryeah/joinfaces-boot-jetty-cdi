package org.philmaster.boot.model;

import org.apache.cayenne.ObjectId;
import org.philmaster.boot.model.auto._Account;

public class Account extends _Account {

	private static final long serialVersionUID = 1L;

	public int getId() {
		ObjectId oj = getObjectId();
		return (oj == null || oj.isTemporary()) ? -1 : (int) oj.getIdSnapshot().get(_Account.ACCOUNT_ID_PK_COLUMN);
	}

}
