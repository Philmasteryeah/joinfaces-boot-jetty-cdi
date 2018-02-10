package org.philmaster.boot.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.primefaces.model.SortOrder;

public class LazySorter<T extends BaseDataObject> implements Comparator<T> {

	private String sortField;
	private SortOrder sortOrder;
	private List<Field> fields;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
		this.fields = new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	public int compare(T obj1, T obj2) {

		// T is the same type, its enough to load all fields from the first obj
		getAllFields(fields, obj1.getClass());

		Object value1 = getAccessibleField(obj1);
		Object value2 = getAccessibleField(obj2);

		if (value1 == null || value2 == null)
			return -1;

		int value = ((Comparable<Object>) value1).compareTo(value2);

		return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	}

	public Object getAccessibleField(Object obj) {
		return getAccessibleField(this.fields, this.sortField, obj);
	}

	// helper for reflection

	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		if (type.getSuperclass() != null)
			getAllFields(fields, type.getSuperclass());
		return fields;
	}

	public static Object getAccessibleField(List<Field> fields, String sortField, Object obj) {
		Field field = fields.stream().filter(f -> f.getName().contains(sortField)).findFirst().orElse(null);
		if (field == null) {
			// TODO logging if field not found
			return null;
		}
		field.setAccessible(true); // cayennes fields are protected, so we need this hack
		try {
			return field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}