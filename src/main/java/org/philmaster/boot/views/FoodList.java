package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

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

	@Getter
	@Setter
	private List<Meal> meals;

	@Getter
	@Setter
	private List<Meal> mealsFiltered;

	@Getter
	@Setter
	private Meal selectedMeal;

	@Inject
	private FoodService fs;

	@PostConstruct
	public void init() {
		try {
			meals = fs.getParsedMeals();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {
		Util.statusMessageInfo("Car Selected", selectedMeal + "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Float.parseFloat(filterText)) > 0;
	}
}
