package org.philmaster.boot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private int taxClass;
	private Date birthDate;
	private Set<String> languageSkills = new HashSet<String>();

	public Person(String id, String name, int taxClass, Date birthDate) {
		this.id = id;
		this.name = name;
		this.taxClass = taxClass;
		this.birthDate = birthDate;
	}

	public void addLanguageSkill(String languageSkill) {
		languageSkills.add(languageSkill);
	}
}
