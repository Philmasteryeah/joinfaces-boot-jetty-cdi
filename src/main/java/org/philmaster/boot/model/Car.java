package org.philmaster.boot.model;

import java.util.function.Predicate;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.auto._Car;
import org.philmaster.boot.util.PMUtil;

public class Car extends _Car {

	private static final long serialVersionUID = 1L;

	public static Predicate<Car> isTest() {
		return c -> "test car".equals(c.getName());
	}

//	public int getId() {
//		ObjectId oj = getObjectId();
//		return (oj == null || oj.isTemporary()) ? -1 : (int) oj.getIdSnapshot().get(_Car.CAR_ID_PK_COLUMN);
//	}

	public static Car createRandomTestCar(ObjectContext context, Client client) {
		Car car = context.newObject(Car.class);
		car.setClient(client);
		car.setName(PMUtil.randomAlphanumericString(10));
		return car;
	}
}
