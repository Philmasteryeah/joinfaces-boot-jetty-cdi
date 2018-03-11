package org.philmaster.boot.model;

import java.util.function.Predicate;

import org.apache.cayenne.ObjectId;
import org.philmaster.boot.model.auto._Car;

public class Car extends _Car {

	private static final long serialVersionUID = 1L;
	// private static final Logger LOGGER = LogManager.getLogger();

	public String objectId; // needed for the sorting, should be removed later

	public static Predicate<Car> isTest() {
		return c -> "test car".equals(c.getName());
	}

	@Override
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId.toString(); // primefaces uses String id for sorting
		super.setObjectId(objectId);
	}

}
