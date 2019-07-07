package org.philmaster.boot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.philmaster.boot.model.Privilege;
import org.philmaster.boot.model.auto._Privilege;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value = "pickListConverter")
public class PickListConverter implements Converter<Object> {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent comp, String value) {
		System.err.println("-> " + value);
		DualListModel<Privilege> model = (DualListModel<Privilege>) ((PickList) comp).getValue();
		System.err.println(model.getSource());
		System.err.println(model.getTarget());
		if (model == null || value == null)
			return null;

		for (Privilege employee : model.getSource()) {
			if (value.equals(getId(employee))) {
				return employee;
			}
		}
		for (Privilege employee : model.getTarget()) {
			if (value.equals(getId(employee))) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent comp, Object value) {
		System.err.println("--> " + value);
		if (value instanceof Privilege)
			return getId((Privilege) value);
		return null;
	}

	private String getId(Privilege priv) {
		if (priv == null)
			return null;
		return priv.id();
	}

}
