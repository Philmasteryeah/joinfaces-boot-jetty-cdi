package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.ExpressionFactory;
import org.philmaster.boot.model.Car;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;
import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CarList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DatabaseService db;

	@Getter
	private Client client;

	private List<Car> cars;

	@Getter
	@Setter
	private Car selectedCar;

	@Getter
	private ObjectContext context;

	@PostConstruct
	public void init() {
		context = db.newContext();
		client = DatabaseService.clientByName(context);

	}

	public void onRowSelect(SelectEvent event) {
		Util.statusMessageInfo("Car Selected", selectedCar.getId() + "");
	}

	public List<Car> getCars() {
		if (cars != null)
			return cars;
		cars = DatabaseService.fetch(context, Car.class, ExpressionFactory.matchExp(client), Car.NAME.ascInsensitive());
		return cars;
	}

	public void refresh() {
		cars = null;
	}

}
