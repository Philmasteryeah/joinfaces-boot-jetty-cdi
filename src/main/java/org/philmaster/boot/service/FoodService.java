package org.philmaster.boot.service;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.philmaster.boot.model.Meal;

/**
 * @author Philmasteryeah
 *
 */

@Named
@ApplicationScoped
public class FoodService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String URL = "https://bestellung-rastenberger.mms-rcs.de/menu/3/2018-06-25/2018-07-01/";

	@PostConstruct
	void init() {
	}

	public List<Meal> getParsedMeals() throws Exception {
		Document doc = Jsoup.connect(URL).get();
		Elements menuRows = doc.getElementById("menu-table_KW").select("tbody").get(0).select("tr");

		List<Meal> meals = new ArrayList<>();

		// erste row ist Header deswegen skippen
		for (int i = 1; i < menuRows.size(); i++) {
			Element menuRow = menuRows.get(i);

			String type = menuRow.select("th").text();

			// skip weekend
			if ("Wochenend-Eintopf".equals(type) || "Sonntagsbraten".equals(type))
				continue;

			Elements rows = menuRow.select("td");

			// über tage iterieren rows.size() durch 5 ersetzt bis Freitag
			for (int dayIndex = 0; dayIndex < 5; dayIndex++) {
				String desc = rows.select("td").get(dayIndex).text();
				String kcal = parseKcal(desc);
				DayOfWeek day = DayOfWeek.of(dayIndex + 1);
				float price = Meal.TypePrice.getPrice(type);

				Meal meal = new Meal(day, type, desc, price, kcal);

				meals.add(meal);
			}

			// meals.forEach(System.err::println);

		}
		return meals;

	}

	private static String parseKcal(String desc) {
		Pattern pattern = Pattern.compile("[0-9]+ kcal");
		Matcher matcher = pattern.matcher(desc);
		return matcher.find() ? matcher.group(0) : null;
	}

}
