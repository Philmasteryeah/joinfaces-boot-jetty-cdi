package org.philmaster.boot.service;

import java.io.IOException;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

	// TODO date dynamic
	private static final String URL = "https://www.bestellung-rastenberger.de/menu/3/2018-09-10/2018-09-16/";

	@Inject
	private ImageService is; // testing

	@PostConstruct
	void init() {
	}

	private Document parseUrlToDocument() {
		try {
			return Jsoup.connect(URL).get();
		} catch (IOException e) {
			System.err.println(e); // TODO
		}
		return null;
	}

	public List<Meal> getParsedMeals() {
		Document doc = parseUrlToDocument();
		if (doc == null)
			return null;

		Elements menuRows = doc.getElementById("menu-table_KW").select("tbody").get(0).select("tr");

		List<Meal> meals = new ArrayList<>();
		// skip header
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
				desc = desc.replaceAll(kcal, ""); // dont want it two times
				DayOfWeek day = DayOfWeek.of(dayIndex + 1);
				float price = Meal.TypePrice.getPrice(type);

				Meal meal = new Meal(day, type, desc, price, kcal);
// testing without picture
//				String base64 = is.getBase64ImageFromTags(desc);
//				meal.setImageBase64(base64 != null ? base64 : "");

				meals.add(meal);
			}

			// meals.forEach(System.err::println);

		}
		return meals;

	}
// new java11 HTTPRequest Stuff from https://www.logicbig.com/tutorials/core-java-tutorial/java-11-changes/http-client.html
//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("http://www.example.com/"))
//            .GET()//used by default if we don't specify
//            .build();
////creating response body handler
//HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
//
////sending request and receiving response via HttpClient
//HttpClient client = HttpClient.newHttpClient();
//CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, bodyHandler);
//future.thenApply(HttpResponse::body)
//.thenAccept(System.out::println)
//.join();
//	

	private static String parseKcal(String desc) {
		Pattern pattern = Pattern.compile("[0-9]+ kcal");
		Matcher matcher = pattern.matcher(desc);
		return matcher.find() ? matcher.group(0) : null;
	}

}
