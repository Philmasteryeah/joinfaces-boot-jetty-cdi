package org.philmaster.boot.converter;

import java.util.List;
import java.util.stream.Stream;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.cayenne.BaseDataObject;
import org.philmaster.boot.util.PMUtil;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 * picklistconverter for cayenne base data objects
 * 
 * @author Philmaster
 *
 */
@FacesConverter(value = "pickListConverter")
public class PickListConverter implements Converter<Object> {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent comp, String value) {
		@SuppressWarnings("unchecked")
		DualListModel<BaseDataObject> model = (DualListModel<BaseDataObject>) ((PickList) comp).getValue();
		if (model == null || value == null)
			return null;
		return Stream.of(model.getSource(), model.getTarget())
				.flatMap(List<BaseDataObject>::stream)
				.filter(p -> value.equals(PMUtil.getObjectId(p)))
				.findFirst()
				.orElse(null);
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent comp, Object value) {
		return value instanceof BaseDataObject ? PMUtil.getObjectId(((BaseDataObject) value)) : null;
	}

}
