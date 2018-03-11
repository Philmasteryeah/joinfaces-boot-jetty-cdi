package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.Car;
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
	private List<Car> cars;

	/// @Getter
	// private List<Car> carsOld;

	@Getter
	@Setter
	private Car selectedCar;

	@Inject
	private DatabaseService db;

	@PostConstruct
	public void init() {
		refreshCarList();

	}

	public void refreshCarList() {
		cars = db.fetchAll(Car.class);
		// cars = new LazyCarDataModel(carsOld);
	}

	public void onRowSelect(SelectEvent event) {
		Util.statusMessageInfo("Car Selected", selectedCar.getObjectId().toString());
	}

	public void onRowUnselect(UnselectEvent event) {
		// Util.statusMessageInfo("Car Unselected", ((Cars)
		// event.getObject()).getName());
	}

	public void actionAdd(ActionEvent actionEvent) {
		Car car = db.createNew(Car.class);
		car.setName("test car");
		
		db.getContext().commitChanges();
		Util.statusMessageInfo("Welcome", "test");
		refreshCarList();
	}

	public void actionSave(ActionEvent actionEvent) {
		db.getContext().commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
		refreshCarList();
	}

}
