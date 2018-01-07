package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Cars;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.util.Util;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class DownloadView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private List<Cars> carsList;

	@Getter
	@Setter
	private Cars selectedCar;

	@Inject
	private DatabaseService db;

	@PostConstruct
	public void init() {
		ObjectContext oc = db.getContext();
		Cars car = Cars.createNewCar(oc);
		car.setName("test car");
		// System.err.println(car.getName() + " name");

		// Cars caar = Cars.fetchCarByName(oc, "test car");
		// System.err.println(caar+" caar");
		carsList = Cars.fetchAllCars(oc);

		// carsList.stream().forEach(carr -> System.out.println(carr.getName()));
		// System.err.println(carsList.size());
	}

	public void onRowSelect(SelectEvent event) {
		System.err.println(selectedCar);
		Util.statusMessageInfo("Car Selected", selectedCar.getObjectId()+"");
	}

	public void onRowUnselect(UnselectEvent event) {
		//Util.statusMessageInfo("Car Unselected", ((Cars) event.getObject()).getName());
	}

}
