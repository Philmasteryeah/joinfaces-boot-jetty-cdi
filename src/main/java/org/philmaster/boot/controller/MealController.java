package org.philmaster.boot.controller;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.philmaster.boot.model.Meal;
import org.philmaster.boot.service.FoodService;
import org.philmaster.boot.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealController {

	@Inject
	private FoodService fs;

	@Inject
	private ImageService is; // testing

	@RequestMapping(path = "/rest/testMeal", method = RequestMethod.GET)
	public ResponseEntity<Meal> getTestMeal() {
		return new ResponseEntity<Meal>(Meal.getTestMeal(), HttpStatus.OK);
	}

	@RequestMapping(path = "/rest/testMeals", method = RequestMethod.GET)
	public ResponseEntity<List<Meal>> getTestMeals() {
		return new ResponseEntity<List<Meal>>(Arrays.asList(Meal.getTestMeal(), Meal.getTestMeal()), HttpStatus.OK);
	}

	@RequestMapping(path = "/rest/meals", method = RequestMethod.GET)
	public ResponseEntity<List<Meal>> getMeals() {
		return new ResponseEntity<List<Meal>>(fs.getParsedMeals(), HttpStatus.OK);
	}

	@RequestMapping(path = "/rest/test", method = RequestMethod.GET)
	public ResponseEntity<String> getTest() {
		return new ResponseEntity<String>(is.getTestImage(), HttpStatus.OK);
	}
}
