package org.philmaster.boot.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.philmaster.boot.util.Util;
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
 *         programmatic menu generation
 * 
 *
 */
@Getter
@Named
@Configuration
@PropertySource("classpath:menu.properties")
@ConfigurationProperties(prefix = "menu")
public class IndexView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	public static class MenuItem implements Serializable {

		private static final long serialVersionUID = 1L;

		private String title;
		private String pageName;
		private String icon;
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
		item.setAjax(true);
		item.setUpdate("@form");
		item.setCommand(commandString(menuItem.getPageName()));
		return item;
	}

	@SuppressWarnings("el-syntax")
	private String commandString(String pageName) {
		StringBuilder sb = new StringBuilder();
		sb.append("#{sessionBean.setPage('");
		sb.append(pageName);
		sb.append("')}");
		return sb.toString();
	}

	// dont needed

	public void save() {
		Util.statusMessageInfo("Success", "Data saved");
	}

	public void update() {
		Util.statusMessageInfo("Success", "Data updated");
	}

	public void delete() {
		Util.statusMessageInfo("Success", "Data deleted");
	}

}
