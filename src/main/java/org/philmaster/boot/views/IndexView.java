package org.philmaster.boot.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Philmasteryeah
 * 
 *         add new menu items here
 *
 */
@Getter
@Named
@ViewScoped
@Configuration
@PropertySource("classpath:menu.properties")
@ConfigurationProperties(prefix = "menu")
public class IndexView {

    @Getter
    @Setter
    public static class MenuItem {
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

    private DefaultMenuItem createPageItem(MenuItem menuItem) {
	DefaultMenuItem item = new DefaultMenuItem(menuItem.getTitle());
	item.setIcon(menuItem.getIcon());
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('" + menuItem.getPageName() + "')}");
	return item;
    }

    // dont needed

    public void save() {
	addMessage("Success", "Data saved");
    }

    public void update() {
	addMessage("Success", "Data updated");
    }

    public void delete() {
	addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
