package org.philmaster.boot.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.philmaster.boot.model.auto._Car;

public class Car extends _Car {

	private static final long serialVersionUID = 1L;

	public String getAttribute(String label) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		System.err.println(label + "-------");
		if ("objectId".equals(label))
			return getObjectId().toString();
		//String methodName = "getName";
		//String getNameMethod = (String) getClass().getMethod(methodName).invoke(this);
		
		
		return getName();
	}

}
