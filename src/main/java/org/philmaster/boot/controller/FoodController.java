package org.philmaster.boot.controller;

import java.util.Arrays;
import java.util.List;

import org.philmaster.boot.model.Meal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

	@RequestMapping(path = "/test/meal", method = RequestMethod.GET)
	public ResponseEntity<Meal> getTestMeal() {
		return new ResponseEntity<Meal>(Meal.getTestMeal(), HttpStatus.OK);
	}

	@RequestMapping(path = "/test/meals", method = RequestMethod.GET)
	public ResponseEntity<List<Meal>> getTestMeals() {
		return new ResponseEntity<List<Meal>>(Arrays.asList(Meal.getTestMeal(), Meal.getTestMeal()), HttpStatus.OK);
	}

}
