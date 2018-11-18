package org.philmaster.boot.model;

import java.util.function.Predicate;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.ObjectId;
import org.philmaster.boot.model.auto._Car;
import org.philmaster.boot.util.Util;

public class Car extends _Car {

	private static final long serialVersionUID = 1L;
	// private static final Logger LOGGER = LogManager.getLogger();

	public static Predicate<Car> isTest() {
		return c -> "test car".equals(c.getName());
	}

	public int getId() {
		ObjectId oj = getObjectId();
		return (oj == null || oj.isTemporary()) ? -1 : (int) oj.getIdSnapshot().get(_Car.CAR_ID_PK_COLUMN);
	}

	public static Car createRandomTestCar(ObjectContext context, Client client) {
		Car car = context.newObject(Car.class);
		car.setClient(client);
		car.setName(Util.randomAlphanumericString(10));
		return car;
	}
}
