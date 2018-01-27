package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Philmasteryeah
 * 
 *         programmatic menu generation use menu.properties for configuration
 *
 */
@Getter
@Named
@Configuration
@PropertySource("classpath:menu.properties")
@ConfigurationProperties(prefix = "menu")
public class MenuView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	public static class MenuItem implements Serializable {

		private static final long serialVersionUID = 1L;

		private String title;
		private String pageName;
		private String icon;
		private boolean isAjax = false;
	}

	private List<MenuItem> items = new ArrayList<>();

	private MenuModel model;

	@PostConstruct
	public void init() {
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
