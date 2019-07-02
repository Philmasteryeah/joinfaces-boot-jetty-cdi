package org.philmaster.boot.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.philmaster.boot.model.Privilege;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value = "pickListConverter")
public class PickListConverter implements Converter<Object> {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		System.err.println("get "+context + " " + component + " " + value);
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		System.err.println("gets "+context + " " + component + " " + value);
		// TODO Auto-generated method stub
		return null;
	}

//	public Object getAsObject(FacesContext context, UIComponent component, String value) {
//		return getObjectFromUIPickListComponent(component, value);
//	}
//
//	public String getAsString(FacesContext context, UIComponent component, Object object) {
//		String string;
//		if (object == null) {
//			string = "";
//		} else {
//			try {
//				string = String.valueOf(((Privilege) object).getName());
//			} catch (ClassCastException cce) {
//				throw new ConverterException();
//			}
//		}
//		return string;
//	}
//
//	@SuppressWarnings("unchecked")
//	private Privilege getObjectFromUIPickListComponent(UIComponent component, String value) {
//		final DualListModel<Privilege> dualList;
//		try {
//			dualList = (DualListModel<Privilege>) ((PickList) component).getValue();
//			Privilege resource = getObjectFromList(dualList.getSource(), Long.valueOf(value));
//			if (resource == null) {
//				resource = getObjectFromList(dualList.getTarget(), Long.valueOf(value));
//			}
//
//			return resource;
//		} catch (ClassCastException cce) {
//			throw new ConverterException();
//		} catch (NumberFormatException nfe) {
//			throw new ConverterException();
//		}
//	}
//
//	private Privilege getObjectFromList(final List<?> list, final Long identifier) {
//		for (final Object object : list) {
//			final Privilege resource = (Privilege) object;
//			return resource;
////			if (resource.getId().equals(identifier)) {
////				return resource;
////			}
//		}
//		return null;
//	}

}
