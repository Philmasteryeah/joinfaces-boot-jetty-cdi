package org.philmaster.boot.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localDateTimeConverter")
public class LocalDateTimeConverter implements Converter<Object> {

	private static final DateTimeFormatter fmt = new DateTimeFormatterBuilder().appendPattern("dd.MM.yy")
			.toFormatter();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO check all possible formats
		return LocalDate.parse(value, fmt);

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd.MM.yy"));
	}

}