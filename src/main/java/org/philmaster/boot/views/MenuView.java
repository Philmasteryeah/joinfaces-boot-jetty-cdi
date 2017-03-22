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

    public MenuModel getModel() {
	return model;
    }

    @PostConstruct
    public void init() {
	model = new DefaultMenuModel();
	DefaultSubMenu firstSubmenu = new DefaultSubMenu("Administration");
	firstSubmenu.addElement(createPageItem("ui-icon-wrench", "Options", "options"));
	firstSubmenu.addElement(createPageItem("ui-icon-person", "Users", "users"));
	firstSubmenu.addElement(createPageItem("ui-icon-key", "Roles and Rights", "rolesAndRights"));
	model.addElement(firstSubmenu);
	DefaultSubMenu secondSubmenu = new DefaultSubMenu("Documents");
	secondSubmenu.addElement(createPageItem("ui-icon-circle-arrow-n", "Upload", "upload"));
	secondSubmenu.addElement(createPageItem("ui-icon-circle-arrow-s", "Download", "download"));
	model.addElement(secondSubmenu);
    }

    private DefaultMenuItem createPageItem(String icon, String name, String page) {
	DefaultMenuItem item = new DefaultMenuItem(name);
	item.setIcon(icon);
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('" + page + "')}");
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
