package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.cayenne.exp.ExpressionFactory;
import org.philmaster.boot.model.Car;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.PMContextListBean;

@Named
@ViewScoped
public class CarList extends PMContextListBean<Car> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Car> initItems() {
		return DatabaseService.fetch(getContext(), Car.class, ExpressionFactory.matchExp(getClient()),
				Car.NAME.ascInsensitive());
	}

	public List<Car> getCars() {
		return getItems();
	}

}
