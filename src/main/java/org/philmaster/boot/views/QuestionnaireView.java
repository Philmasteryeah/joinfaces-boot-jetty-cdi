package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Questionnaire.Question;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

@Named
@ViewScoped
public class QuestionnaireView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Question> questions;

	@PostConstruct
	public void init() {
//		questions = new ArrayList<>();
//		questions.add(new Car("Y25YIH5", "Fiat", 2014, "Black", 10000, true));
//		questions.add(new Car("JHF261G", "BMW", 2013, "Blue", 50000, true));
//		questions.add(new Car("HSFY23H", "Ford", 2012, "Black", 35000, false));
//		questions.add(new Car("GMDK353", "Volvo", 2014, "White", 40000, true));
//		questions.add(new Car("345GKM5", "Jaguar", 2011, "Gray", 48000, false));
	}

	public List<Question> getQuestion() {
		return questions;
	}

	public void onTabChange(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab()
				.getTitle());
		FacesContext.getCurrentInstance()
				.addMessage(null, msg);
	}

	public void onTabClose(TabCloseEvent event) {
		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab()
				.getTitle());
		FacesContext.getCurrentInstance()
				.addMessage(null, msg);
	}

}
