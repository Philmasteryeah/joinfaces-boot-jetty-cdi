package org.philmaster.boot.views;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Car;
import org.philmaster.boot.session.ContextDetailBean;

@Named
@ViewScoped
public class CarDetail extends ContextDetailBean<Car> implements Serializable {

	private static final long serialVersionUID = 1L;

	public void actionAdd(ActionEvent actionEvent) {
//		for (int i = 0; i < 10; i++) {
//			Car.createRandomTestCar(context, client);
//		}		
//	
//		Util.statusMessageInfo("Welcome", "saved");

	}

}
