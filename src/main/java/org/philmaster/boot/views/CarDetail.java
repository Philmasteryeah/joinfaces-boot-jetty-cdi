package org.philmaster.boot.views;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.philmaster.boot.model.Car;
import org.philmaster.boot.session.ContextDetailBean;

@Named
@SessionScoped
public class CarDetail extends ContextDetailBean<Car> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Car> initClass() {
		return Car.class;
	}

	public void actionAdd(ActionEvent actionEvent) {
//		for (int i = 0; i < 10; i++) {
//			Car.createRandomTestCar(context, client);
//		}		
//		//context.commitChanges();
//		Util.statusMessageInfo("Welcome", "saved");

	}

}
