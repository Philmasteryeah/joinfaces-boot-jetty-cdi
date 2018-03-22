package org.philmaster.boot.model;

import java.util.List;

import org.philmaster.boot.model.auto._Client;

public class Client extends _Client {

	private static final long serialVersionUID = 1L;

	public int getId() {
		if (getObjectId() == null || getObjectId().isTemporary())
			return -1;
		return (int) getObjectId().getIdSnapshot().get(_Client.CLIENT_ID_PK_COLUMN);
	}

	public List<Car> cars() {
		return getCars();
	}

}
