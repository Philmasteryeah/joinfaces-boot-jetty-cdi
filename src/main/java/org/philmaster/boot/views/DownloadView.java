package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
		refreshCarList();
	}

	public void refreshCarList() {
		ObjectContext oc = db.getContext();
		carsList = Cars.fetchAllCars(oc);
	}
	
	public void onRowSelect(SelectEvent event) {
		System.err.println(selectedCar);
		Util.statusMessageInfo("Car Selected", selectedCar.getObjectId()+"");
	}

	public void onRowUnselect(UnselectEvent event) {
		//Util.statusMessageInfo("Car Unselected", ((Cars) event.getObject()).getName());
	}
	
	public void actionAdd(ActionEvent actionEvent) {
		ObjectContext oc = db.getContext();
		Cars car = Cars.createNewCar(oc);
		car.setName("test car");
		Util.statusMessageInfo("Welcome", "test");
		refreshCarList();
	}
	
	public void actionSave(ActionEvent actionEvent) {
		db.getContext().commitChanges();
		Util.statusMessageInfo("Welcome", "saved");
		refreshCarList();
	}

}
