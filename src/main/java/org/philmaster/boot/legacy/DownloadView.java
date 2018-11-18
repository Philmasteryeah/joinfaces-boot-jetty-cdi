package org.philmaster.boot.legacy;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
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
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class DownloadView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private List<Car> cars;

	@Getter
	@Setter
	private Car selectedCar, testCar;

	@Getter
	@Setter
	private int currentLevel = 1;

	@Getter
	private Client client;

	@Inject
	private DatabaseService db;

	private ObjectContext context;

	@PostConstruct
	public void init() {
		context = db.newContext();

		client = DatabaseService.fetchClient(context);

		cars = DatabaseService.fetch(context, Car.class, ExpressionFactory.matchExp(client));

	}

	public void onRowSelect(SelectEvent event) {
		Util.statusMessageInfo("Car Selected", selectedCar.getId() + "");
	}

	public void onRowUnselect(UnselectEvent event) {
		// Util.statusMessageInfo("Car Unselected", ((Cars)
		// event.getObject()).getName());
	}

	public void actionAdd(ActionEvent actionEvent) {
		testCar = context.newObject(Car.class);
		testCar.setClient(client);
		// car.setName("test car");

		Util.statusMessageInfo("Welcome", "test");
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
		currentLevel = 1;
	}

}
