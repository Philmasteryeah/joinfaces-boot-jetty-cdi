package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.model.Meal;
import org.philmaster.boot.service.FoodService;
import org.philmaster.boot.util.Util;
import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class FoodList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Setter
	private List<Meal> meals;

	@Getter
	@Setter
	private Meal selectedMeal;

	@Inject
	private FoodService fs;

	@PostConstruct
	public void init() {
	}

	public void onRowSelect(SelectEvent event) {
		Util.statusMessageInfo("Car Selected", selectedMeal + "");
	}

	public List<Meal> getMeals() {
		if (meals == null)
			try {
				meals = fs.getParsedMeals();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		return meals;
	}

}
