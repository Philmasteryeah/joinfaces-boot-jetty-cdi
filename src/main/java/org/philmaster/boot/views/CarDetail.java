package org.philmaster.boot.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Car;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class CarDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Car detailObject;

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
		client = DatabaseService.clientByName(context);
	}

	public String handleDetailObject(Car car) {
		System.err.println("! "+car);
		if (car == null) {
			
			// new object
			detailObject = context.newObject(Car.class);
			detailObject.setClient(client);
		} else {
			// local instance of object
			detailObject = context.localObject(car);
		}
		return "carDetail?faces-redirect=true";
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
		currentLevel = 1;
	}
	
	public void actionAdd(ActionEvent actionEvent) {
		
//		for (int i = 0; i < 10; i++) {
//			Car.createRandomTestCar(context, client);
//		}		
//		
//		
//		//context.commitChanges();
//		Util.statusMessageInfo("Welcome", "saved");
		
	}

}
