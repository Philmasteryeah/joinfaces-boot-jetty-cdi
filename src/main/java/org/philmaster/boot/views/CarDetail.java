package org.philmaster.boot.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Car;
import org.philmaster.boot.model.Questionnaire;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.ContextDetailBean;

@Named
@RequestScoped
public class CarDetail extends ContextDetailBean<Car> {

	@PostConstruct
	public void init() {

	}

	public void actionAdd(ActionEvent actionEvent) {
//		for (int i = 0; i < 10; i++) {
//			Car.createRandomTestCar(context, client);
//		}		
//	
//		Util.statusMessageInfo("Welcome", "saved");

	}

}
