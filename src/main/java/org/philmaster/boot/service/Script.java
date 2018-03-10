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

	private String test = "var resourceURL = new java.net.URL('http://localhost:9090/test');\r\n" + 
			"var urlConnection = resourceURL.openConnection();\r\n" + 
			"var inputStream = new java.io.InputStreamReader(urlConnection\r\n" + 
			"        .getInputStream());\r\n" + 
			"var bufferedReader = new java.io.BufferedReader(inputStream);\r\n" + 
			"var inputLine = \"\";\r\n" + 
			"    while((line = bufferedReader.readLine()) != null)\r\n" + 
			"    	inputLine += line;\r\n" + 
			"bufferedReader.close();\r\n" + 
			"print(String(inputLine));\r\n" + 
			"";

	@PostConstruct
	void init() {
		engine = new ScriptEngineManager().getEngineByName("nashorn");
		query = "var greeting='hello world';" + "print(greeting);"; // default test
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
