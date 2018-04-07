package org.philmaster.boot.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.Car;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CarDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Car selectedCar, newCar;

	@Getter
	private Client client;

	@Getter
	@Setter
	private int currentLevel = 1;

	@Inject
	private DatabaseService db;

	@PostConstruct
	public void init() {
		client = db.clientByName();

		newCar = db.createNew(Car.class);
		newCar.setClientId(client.getId());
	}

	public void actionSave(ActionEvent actionEvent) {
		db.getContext().commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
		currentLevel = 1;
	}

}
