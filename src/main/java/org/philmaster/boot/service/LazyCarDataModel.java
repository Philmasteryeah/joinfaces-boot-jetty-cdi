package org.philmaster.boot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.philmaster.boot.model.Car;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyCarDataModel extends LazyDataModel<Car> {

	private static final long serialVersionUID = 1L;

	private List<Car> datasource;

	public LazyCarDataModel(List<Car> datasource) {
		this.datasource = datasource;
	}

	@Override
	public Car getRowData(String rowKey) {
		for (Car car : datasource)
			if (car.getObjectId().toString().equals(rowKey))
				return car;
		return null;
	}

	@Override
	public Object getRowKey(Car car) {
		return car.getObjectId().toString();
	}

	@Override
	public List<Car> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<Car> data = new ArrayList<Car>();
		// filter Object should be a predicate
		// data = data.stream().filter(Car.isTest()).collect(Collectors.toList());

		// filter
		for (Car car : datasource) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(car);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazySorter<Car>(sortField, sortOrder, Car.class));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}
}