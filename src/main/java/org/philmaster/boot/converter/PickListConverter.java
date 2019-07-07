package org.philmaster.boot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.philmaster.boot.model.Privilege;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value = "pickListConverter")
public class PickListConverter implements Converter<Object> {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent comp, String value) {
		DualListModel<Privilege> model = (DualListModel<Privilege>) ((PickList) comp).getValue();
		if (model == null || value == null)
			return null;

		for (Privilege privilege : model.getSource()) {
			if (value.equals(privilege.id())) {
				return privilege;
			}
		}
		for (Privilege privilege : model.getTarget()) {
			if (value.equals(privilege.id())) {
				return privilege;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent comp, Object value) {
		if (value instanceof Privilege)
			return ((Privilege) value).id();
		return null;
	}

}
