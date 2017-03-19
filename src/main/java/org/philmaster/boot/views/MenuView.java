package org.philmaster.boot.views;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
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

	// First submenu
	DefaultSubMenu firstSubmenu = new DefaultSubMenu("Administration");

	DefaultMenuItem item = new DefaultMenuItem("Options");
	item.setIcon("ui-icon-wrench");
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('fileUpload')}");

	firstSubmenu.addElement(item);
	
	// ui-icon-script

	item = new DefaultMenuItem("Roles and Rights");
	item.setIcon("ui-icon-key");
	item.setAjax(true);
	item.setUpdate("@form");
	item.setCommand("#{pagerBean.setPage('main')}");
	firstSubmenu.addElement(item);

	item = new DefaultMenuItem("Users");
	item.setIcon("ui-icon-person");
	firstSubmenu.addElement(item);

	model.addElement(firstSubmenu);

	// Second submenu
	DefaultSubMenu secondSubmenu = new DefaultSubMenu("Documents");

	item = new DefaultMenuItem("Upload");
	item.setIcon("ui-icon-circle-arrow-n");
	// item.setCommand("#{menuView.save}");
	///item.setUpdate("messages");
	secondSubmenu.addElement(item);

	item = new DefaultMenuItem("Download");
	item.setIcon("ui-icon-circle-arrow-s");
	/// item.setCommand("#{menuView.delete}");
	// item.setAjax(false);
	secondSubmenu.addElement(item);

	model.addElement(secondSubmenu);

    }

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
