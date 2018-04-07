package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.exp.ExpressionFactory;
import org.philmaster.boot.model.Car;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.model.auto._Car;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;

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
	
	@Getter
	@Setter
	private List<Car> cars;


	@PostConstruct
	public void init() {
		client = db.clientByName();
		cars = db.fetch(Car.class, ExpressionFactory.matchExp(_Car.CLIENT_ID.getName(), client.getId()));
	}

	public void actionSave(ActionEvent actionEvent) {
		// not needed on list page but possible
		db.getContext().commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
	}

}
