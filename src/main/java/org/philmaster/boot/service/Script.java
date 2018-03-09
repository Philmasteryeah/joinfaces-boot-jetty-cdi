package org.philmaster.boot.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ApplicationScoped
public class Script {

	private ScriptEngine engine;
	private String query, result;

	@PostConstruct
	void init() {
		engine = new ScriptEngineManager().getEngineByName("nashorn");
		query = "var greeting='hello world';" + "print(greeting);" + "greeting"; // default test
	}

	public void actionRunQuery(ActionEvent actionEvent) {

		try {
			Object scriptResult = engine.eval(query);
			result = scriptResult.toString();
		} catch (Exception e) {
			result = e.getMessage();
		}
		// System.err.println(result + "-------" + query);
	}

}
