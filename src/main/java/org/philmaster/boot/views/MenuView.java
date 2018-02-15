package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.List;

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
 *         programmatic menu generation use menu.properties for configuration
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
		List<MenuItem> items = menuProperties.getItems();
		model = new DefaultMenuModel();
		DefaultSubMenu sub = null;
		for (MenuItem menuItem : items) {
			if (menuItem.getPageName() == null) {
				if (sub != null)
					model.addElement(sub);
				sub = new DefaultSubMenu(menuItem.getTitle());
			} else if (sub != null)
				sub.addElement(createPageItem(menuItem));
		}
		if (sub != null)
			model.addElement(sub);
	}

	private DefaultMenuItem createPageItem(@NonNull MenuItem menuItem) {
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
