package org.philmaster.boot.views;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.framework.ContextListBean;
import org.philmaster.boot.model.Car;

@Named
@RequestScoped
public class CarList extends ContextListBean<Car> {

//	@Override
//	public Class<Car> initClass() {
//		return Car.class;
//	}
}
