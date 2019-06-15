package org.philmaster.boot.legacy;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Car;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class DownloadView {

	@Getter
	@Setter
	private List<Car> cars;

	@Getter
	@Setter
	private Car selectedCar, testCar;

	@Getter
	@Setter
	private int currentLevel = 1;

	private Client client;

	private ObjectContext context;

//	@Inject
//	private SessionBean session;

	@PostConstruct
	public void init() {
//		context = DatabaseService.newContext();
//
//		client = session.getClient();
//
//		cars = DatabaseService.fetch(context, Car.class, ExpressionFactory.matchExp(client));

	}

	public void onRowSelect(SelectEvent event) {
		PMUtil.statusMessageInfo("Car Selected", selectedCar + "");
	}

	public void onRowUnselect(UnselectEvent event) {

	}

	public void actionAdd(ActionEvent actionEvent) {
		testCar = context.newObject(Car.class);
		testCar.setClient(client);

		PMUtil.statusMessageInfo("Welcome", "test");
	}

	public void actionSave(ActionEvent actionEvent) {
		context.commitChanges();
		PMUtil.statusMessageInfo("Welcome", "saved");
		currentLevel = 1;
	}

}
