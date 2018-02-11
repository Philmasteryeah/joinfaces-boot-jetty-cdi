package org.philmaster.boot.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.philmaster.boot.util.Util;
import org.primefaces.model.SortOrder;

public class LazySorter<T extends BaseDataObject> implements Comparator<T> {

	private String sortField;
	private SortOrder sortOrder;
	private List<Field> fields;

	public LazySorter(String sortField, SortOrder sortOrder, Class<T> type) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
		this.fields = Util.getAllFields(new ArrayList<>(), type);
	}

	@SuppressWarnings("unchecked")
	public int compare(T obj1, T obj2) {

		Object value1 = getAccessibleField(obj1);
		Object value2 = getAccessibleField(obj2);

		if (value1 == null || value2 == null)
			return -1;

		int value = ((Comparable<Object>) value1).compareTo(value2);

		return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	}

	private Object getAccessibleField(Object obj) {
		return Util.getAccessibleField(this.fields, this.sortField, obj);
	}

}