package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Account;
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

	private List<Account> accounts;
	private List<SelectItem> availableLanguageSkills = null;
	private List<String> selectedLanguageSkills = new ArrayList<String>();
	private String languageSkillToAdd;
	private int currentLevel = 1;

	@PostConstruct
	void init() {
		accounts = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();
		calendar.set(1972, 5, 25);

//		accounts.add(new Account("Max", "Mustermann"));
//		calendar.set(1981, 12, 10);
//		accounts.add(new Account("Sara", "Schmidt"));
//		calendar.set(1968, 2, 13);
//		accounts.add(new Account("Jasper", "Morgan"));

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

	public String saveSuccess(Account person) {
		if (person == null)
			return null;
		Util.statusMessageInfo("Info", "Person " + person.getNameFirst() + " has been saved");
		return null;
	}

	public String saveFailure(Account person) {
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

		Util.statusMessageError("Error", "Person " + person.getNameFirst() + " could not be saved");

		return null;
	}

	public String delete(Account acco) {
		if (acco == null)
			return null;
		for (Account acc : accounts) {
			if (acc.getNameFirst().equals(acco.getNameFirst())) {
				accounts.remove(acc);
				break;
			}
		}

		return null;
	}

	public void addLanguageSkill(Account person) {
		if (person == null)
			return;
		if (languageSkillToAdd != null) {
			// person.addLanguageSkill(languageSkillToAdd);
		}

		languageSkillToAdd = null;
	}
}
