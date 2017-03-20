package org.philmaster.boot.views;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.Car;
import org.philmaster.boot.service.CarService;

@Named
@ViewScoped
public class DataExporterView {
    private List<Car> cars;

    @Inject
    private CarService service;

    @PostConstruct
    public void init() {
	cars = service.createCars(100);
    }

    public List<Car> getCars() {
	return cars;
    }

    public void setService(CarService service) {
	this.service = service;
    }
}
