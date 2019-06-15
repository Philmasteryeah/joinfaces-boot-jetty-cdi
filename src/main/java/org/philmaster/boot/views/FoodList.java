package org.philmaster.boot.views;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.philmaster.boot.model.Meal;
import org.philmaster.boot.service.FoodService;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class FoodList {

	private static final Logger LOGGER = LogManager.getLogger(FoodList.class);

	@Getter
	@Setter
	private List<Meal> meals;

	@Getter
	@Setter
	private List<Meal> mealsFiltered;

	@Getter
	@Setter
	private Meal selectedMeal;

	@PostConstruct
	public void init() {
		try {
			meals = FoodService.getParsedMeals();
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
		}
	}

	public void onRowSelect(SelectEvent event) {
		PMUtil.statusMessageInfo("meal selected", selectedMeal + "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null
				: filter.toString()
						.trim();
		if (filterText == null || filterText.equals(""))
			return true;
		if (value == null)
			return false;
		return ((Comparable) value).compareTo(Float.parseFloat(filterText)) > 0;
	}
}
