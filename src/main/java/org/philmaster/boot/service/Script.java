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

	private static String test = "var resourceURL = new java.net.URL('http://localhost:9090/test');\r\n"
			+ "var urlConnection = resourceURL.openConnection();\r\n"
			+ "var inputStream = new java.io.InputStreamReader(urlConnection\r\n" + "        .getInputStream());\r\n"
			+ "var bufferedReader = new java.io.BufferedReader(inputStream);\r\n" + "var inputLine = \"\";\r\n"
			+ "    while((line = bufferedReader.readLine()) != null)\r\n" + "    	inputLine += line;\r\n"
			+ "bufferedReader.close();\r\n" + "print(String(inputLine));\r\n" + "";

	private static String test2 = "var input = [\r\n" + "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key1\", xPath:\"value1\", description:\"value1\"},\r\n"
			+ "  {dbName:\"key2\", xPath:\"value2\", description:\"value1\"}\r\n" + "];\r\n" + "\r\n"
			+ "for(var i = 0; i < input.length; i++)\r\n" + "{\r\n" + "   print(input[i].dbName);\r\n"
			+ "   print(input[i].xPath);\r\n" + "   print(input[i].description);\r\n" + "}\r\n" + "\r\n"
			+ "function xmlToForm(dbName, xPath, description){\r\n" + "	var xmlVal = xml.getValue(xPath);\r\n"
			+ "  	// Handle date, boolean usw.\r\n" + "    print('handle '+description);\r\n"
			+ "  	form.setValue(xmlVal, dbName);\r\n" + "}\r\n" + "\r\n"
			+ "function formToXml(dbName, xPath, description){\r\n" + "	var formVal = form.getValue(dbName);\r\n"
			+ "  	// Handle date, boolean usw.\r\n" + "  	print('handle '+description);\r\n"
			+ "  	xml.setValue(formValue, xPath);\r\n" + "  \r\n" + "}\r\n" + "\r\n" + "";

	private static String test3 = "var greeting='hello world';" + "print(greeting);";
	
	@PostConstruct
	void init() {
		engine = new ScriptEngineManager().getEngineByName("nashorn");
		query = test3; // default test3
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
