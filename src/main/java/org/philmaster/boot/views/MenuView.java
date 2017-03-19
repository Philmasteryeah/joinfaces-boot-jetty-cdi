package org.philmaster.boot.views;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 * @author Philmasteryeah
 * 
 *         add new menu items here
 *
 */

@Named
@ViewScoped
public class MenuView {

    private MenuModel model;

    @PostConstruct
    public void init() {
	model = new DefaultMenuModel();

	DefaultSubMenu firstSubmenu = new DefaultSubMenu("Administration");

	DefaultMenuItem item = new DefaultMenuItem("Options");
	item.setIcon("ui-icon-wrench");
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('options')}");
	firstSubmenu.addElement(item);

	item = new DefaultMenuItem("Roles and Rights");
	item.setIcon("ui-icon-key");
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('rolesAndRights')}");
	firstSubmenu.addElement(item);

	item = new DefaultMenuItem("Users");
	item.setIcon("ui-icon-person");
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('users')}");
	firstSubmenu.addElement(item);

	model.addElement(firstSubmenu);

	DefaultSubMenu secondSubmenu = new DefaultSubMenu("Documents");

	item = new DefaultMenuItem("Upload");
	item.setIcon("ui-icon-circle-arrow-n");
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('fileUpload')}");
	secondSubmenu.addElement(item);

	item = new DefaultMenuItem("Download");
	item.setIcon("ui-icon-circle-arrow-s");
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('users')}");
	secondSubmenu.addElement(item);

	model.addElement(secondSubmenu);

    }

    // dont needed

    public MenuModel getModel() {
	return model;
    }

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
