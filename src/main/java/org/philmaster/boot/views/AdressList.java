package org.philmaster.boot.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.philmaster.boot.model.Adress;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class AdressList {

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

		PMUtil.statusMessageInfo("Selected", "'" + selectedItem + "'");
	}

}
