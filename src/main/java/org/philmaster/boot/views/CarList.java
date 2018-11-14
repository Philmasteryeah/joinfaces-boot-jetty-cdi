package org.philmaster.boot.views;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Car;
import org.philmaster.boot.session.ContextListBean;

@Named
@ViewScoped
public class CarList extends ContextListBean<Car> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<Car> initClass() {
		return Car.class;
	}

}
