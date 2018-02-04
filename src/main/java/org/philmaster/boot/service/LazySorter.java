package org.philmaster.boot.service;

import java.util.Comparator;

import org.apache.cayenne.BaseDataObject;
import org.primefaces.model.SortOrder;

public class LazySorter<T extends BaseDataObject> implements Comparator<T> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(T car1, T car2) {
		try {
			// TODO
			// Object value1 = T.class.getField(this.sortField).get(car1);
			// Object value2 = Car.class.getField(this.sortField).get(car2);
			Object value1 = "1";
			Object value2 = "1";

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}