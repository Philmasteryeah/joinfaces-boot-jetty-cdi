package org.philmaster.boot.model;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectQuery;
import org.philmaster.model.car._Cars;

public class Cars extends _Cars {

	private static final long serialVersionUID = 1L;

	public static Cars createNewCar(ObjectContext oc) {
		return oc.newObject(Cars.class);
	}

	public static Cars createNewCar(ObjectContext oc, String name) {
		Cars car = oc.newObject(Cars.class);
		car.setName(name);
		return car;
	}

	public static List<Cars> fetchAllCars(ObjectContext oc) {
		return oc.select(SelectQuery.query(Cars.class));
	}

}
