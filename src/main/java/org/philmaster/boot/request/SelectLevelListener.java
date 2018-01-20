package org.philmaster.boot.request;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.extensions.component.masterdetail.SelectLevelEvent;

@Named
@RequestScoped
public class SelectLevelListener implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean errorOccured = false;

	public int handleNavigation(SelectLevelEvent selectLevelEvent) {
		if (errorOccured) {
			return 2;
		} else {
			return selectLevelEvent.getNewLevel();
		}
	}

	public void setErrorOccured(boolean errorOccured) {
		this.errorOccured = errorOccured;
	}
}