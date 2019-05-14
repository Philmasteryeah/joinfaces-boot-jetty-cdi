package org.philmaster.boot.model;

import org.philmaster.boot.model.auto._Client;

public class Client extends _Client {



	public Object id() {
		return objectId.getIdSnapshot()
				.get(_Client.CLIENT_ID_PK_COLUMN);
	}

}
