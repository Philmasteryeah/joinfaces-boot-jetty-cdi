package org.philmaster.boot.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectQuery;
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
	private Car newCar;

	@Getter
	private Client client;

	@Getter
	@Setter
	private int currentLevel = 1;

	@Inject
	private DatabaseService db;

	private ObjectContext context;

	@PostConstruct
	public void init() {
		context = db.newContext();

		client = context.select(SelectQuery.query(Client.class)).get(0);
	
		newCar = context.newObject(Car.class);
		newCar.setClientId(client.getId());
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
		currentLevel = 1;
	}

}
