package org.philmaster.boot.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int taxClass;
	private LocalDate birthDate;

	public Person() {

	}

	public Person(String id, String name, int taxClass, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.taxClass = taxClass;
		this.birthDate = birthDate;
	}

}
