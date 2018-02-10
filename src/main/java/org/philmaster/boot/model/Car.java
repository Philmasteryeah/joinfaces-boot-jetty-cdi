package org.philmaster.boot.model;

import java.util.function.Predicate;

import org.apache.cayenne.ObjectId;
import org.philmaster.boot.model.auto._Car;

public class Car extends _Car {

	private static final long serialVersionUID = 1L;

	public String objectId; // needed for the sorting, should be removed later

	public static Predicate<Car> isTest() {
		return c -> "test car".equals(c.getName());
	}

	@Override
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId.toString(); // primefaces uses String id for sorting
		super.setObjectId(objectId);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + (this.getObjectId() != null ? this.getObjectId().hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Car other = (Car) obj;
		if ((this.getObjectId() == null) ? (other.getObjectId() != null)
				: !this.getObjectId().equals(other.getObjectId())) {
			return false;
		}
		return true;
	}
}
