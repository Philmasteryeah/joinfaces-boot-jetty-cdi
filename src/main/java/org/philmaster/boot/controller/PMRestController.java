package org.philmaster.boot.controller;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.philmaster.boot.model.Meal;
import org.philmaster.boot.service.FileService;
import org.philmaster.boot.service.FoodService;
import org.philmaster.boot.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class PMRestController {

	@Inject
	private FileService fis;

	@GetMapping(path = "/testMeal")
	public ResponseEntity<Meal> getTestMeal() {
		return new ResponseEntity<>(Meal.getTestMeal(), HttpStatus.OK);
	}

	@GetMapping(path = "/testMeals")
	public ResponseEntity<List<Meal>> getTestMeals() {
		return new ResponseEntity<>(Arrays.asList(Meal.getTestMeal(), Meal.getTestMeal()), HttpStatus.OK);
	}

	@GetMapping(path = "/meals")
	public ResponseEntity<List<Meal>> getMeals() {
		return new ResponseEntity<>(FoodService.getParsedMeals(), HttpStatus.OK);
	}

	@GetMapping(path = "/test")
	public ResponseEntity<String> getTest() {
		return new ResponseEntity<>(ImageService.getTestImage(), HttpStatus.OK);
	}

	@GetMapping(path = "/test2", produces = "application/json")
	public ResponseEntity<String> getTest2() {
		return new ResponseEntity<>(fis.getStaticFileAsString("test.json"), HttpStatus.OK);
	}

	@GetMapping(path = "/quest", produces = "application/json")
	public ResponseEntity<String> getQuest() {
		return new ResponseEntity<>(fis.getStaticFileAsString("questionnaire.json"), HttpStatus.OK);
	}

}
