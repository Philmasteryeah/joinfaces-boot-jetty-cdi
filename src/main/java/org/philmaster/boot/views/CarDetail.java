package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextDetailBean;
import org.philmaster.boot.model.Car;

@Named
@RequestScoped
public class CarDetail extends ContextDetailBean<Car> {

	public void actionAdd(ActionEvent actionEvent) {
//		for (int i = 0; i < 10; i++) {
//			Car.createRandomTestCar(context, client);
//		}		
//	
//		Util.statusMessageInfo("Welcome", "saved");

	}

}
