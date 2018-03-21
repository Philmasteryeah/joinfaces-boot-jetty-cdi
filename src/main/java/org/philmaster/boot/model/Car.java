package org.philmaster.boot.model;

import java.util.function.Predicate;

import org.philmaster.boot.model.auto._Car;

public class Car extends _Car {

	private static final long serialVersionUID = 1L;
	// private static final Logger LOGGER = LogManager.getLogger();

	public static Predicate<Car> isTest() {
		return c -> "test car".equals(c.getName());
	}

	public int getId() {
		if (getObjectId() == null || getObjectId().isTemporary())
			return -1;
		return (int) getObjectId().getIdSnapshot().get(_Car.CAR_ID_PK_COLUMN);
	}

}
