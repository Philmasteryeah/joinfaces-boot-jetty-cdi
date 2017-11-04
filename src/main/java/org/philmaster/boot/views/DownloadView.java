package org.philmaster.boot.views;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.Car;
import org.philmaster.boot.service.CarService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class DownloadView {

	@Getter
	private List<Car> cars;

	@Setter
	@Inject
	private CarService service;

	@PostConstruct
	public void init() {
		cars = service.createCars(100);
	}
}
