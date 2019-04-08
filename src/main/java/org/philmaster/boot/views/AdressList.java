package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Adress;
import org.philmaster.boot.util.Util;
import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class AdressList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private List<Adress> items, itemsFiltered;

	@Getter
	@Setter
	private Adress selectedItem;

//	@Inject
//	private SessionBean session;

	@PostConstruct
	public void init() {
		items = new ArrayList<>();
		items.add(
				new Adress("Deutscher Bundestag", "Platz der Republik 1", "11011", "Berlin", "52.518623", "13.376198"));
	}

	public void onRowSelect(SelectEvent event) {
		// TODO
		Util.statusMessageInfo("Selected", "'" + selectedItem + "'");
	}

}
