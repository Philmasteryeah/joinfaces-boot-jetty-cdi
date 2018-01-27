package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.cayenne.map.DbAttribute;
import org.apache.cayenne.map.DbRelationship;
import org.apache.cayenne.map.ObjRelationship;
import org.philmaster.boot.model.Person;
import org.philmaster.boot.request.SelectLevelListener;
import org.philmaster.boot.util.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@ViewScoped
public class RolesAndRightsView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Person> persons;
	private List<SelectItem> availableLanguageSkills = null;
	private List<String> selectedLanguageSkills = new ArrayList<String>();
	private String languageSkillToAdd;
	private int currentLevel = 1;

	@PostConstruct
	void init() {
		persons = new ArrayList<Person>();

		Calendar calendar = Calendar.getInstance();
		calendar.set(1972, 5, 25);
		persons.add(new Person("1", "Max Mustermann", 1, calendar.getTime()));
		calendar.set(1981, 12, 10);
		persons.add(new Person("2", "Sara Schmidt", 2, calendar.getTime()));
		calendar.set(1968, 2, 13);
		persons.add(new Person("3", "Jasper Morgan", 3, calendar.getTime()));

	}

	public RolesAndRightsView() {
	}

	public List<SelectItem> getAvailableLanguageSkills() {
		if (availableLanguageSkills == null) {
			availableLanguageSkills = new ArrayList<SelectItem>();
			availableLanguageSkills.add(new SelectItem("English", "English"));
			availableLanguageSkills.add(new SelectItem("German", "German"));
			availableLanguageSkills.add(new SelectItem("Russian", "Russian"));
			availableLanguageSkills.add(new SelectItem("Turkish", "Turkish"));
			availableLanguageSkills.add(new SelectItem("Dutch", "Dutch"));
			availableLanguageSkills.add(new SelectItem("French", "French"));
			availableLanguageSkills.add(new SelectItem("Italian", "Italian"));
		}

		return availableLanguageSkills;
	}

	public String getLanguageSkillToAdd() {
		return languageSkillToAdd;
	}

	public String saveSuccess(Person person) {
		if (person == null)
			return null;
		Util.statusMessageInfo("Info", "Person " + person.getName() + " has been saved");
		return null;
	}

	public String saveFailure(Person person) {
		if (person == null)
			return null;
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = fc.getELContext();

		SelectLevelListener selectLevelListener;
		try {
			selectLevelListener = (SelectLevelListener) elContext.getELResolver().getValue(elContext, null,
					"selectLevelListener");
			selectLevelListener.setErrorOccured(true);
		} catch (RuntimeException e) {
			throw new FacesException(e.getMessage(), e);
		}

		Util.statusMessageError("Error", "Person " + person.getName() + " could not be saved");

		return null;
	}

	public String delete(Person person) {
		if (person == null)
			return null;
		for (Person pers : persons) {
			if (pers.getId().equals(person.getId())) {
				persons.remove(pers);

				break;
			}
		}

		return null;
	}

	public void addLanguageSkill(Person person) {
		if (person == null)
			return;
		if (languageSkillToAdd != null) {
			person.addLanguageSkill(languageSkillToAdd);
		}

		languageSkillToAdd = null;
	}
}
