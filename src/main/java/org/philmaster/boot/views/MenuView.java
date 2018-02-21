package org.philmaster.boot.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.philmaster.boot.util.MenuProperties;
import org.philmaster.boot.util.MenuProperties.MenuItem;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Philmasteryeah
 * 
 *         programmatic menu generation use application.yml for configuration
 *
 */

@Named
public class MenuView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MenuProperties menuProperties;

	@Getter
	@Setter
	private MenuModel model;

	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();
		menuProperties.getItems().stream().forEach(item -> {
			DefaultSubMenu sbm = new DefaultSubMenu(item.getSubmenuTitle());
			item.getSubitems().forEach(subItem -> sbm.addElement(createMenuItem(subItem)));
			model.addElement(sbm);
		});
	}

	private DefaultMenuItem createMenuItem(@NonNull MenuItem menuItem) {
		DefaultMenuItem item = new DefaultMenuItem(menuItem.getTitle());
		item.setIcon(menuItem.getIcon());
		item.setAjax(menuItem.isAjax());
		// item.setUpdate("@form");
		item.setOutcome(outcomeString(menuItem.getPageName()));
		return item;
	}

	private String outcomeString(String pageName) {
		StringBuilder sb = new StringBuilder();
		sb.append("/views/");
		sb.append(pageName);
		return sb.toString();
	}

}
