package org.philmaster.boot.beans;

import java.io.Serializable;

import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Philmasteryeah
 * 
 *         switch the content pages with this bean
 * 
 *         its very important to use SessionScope here otherwise its will be
 *         reset to 'main 'after menu click
 * 
 *         getPage() will return the name of the current page
 *         getPagePrettyPrinted() user readable printed for info messages
 *
 */

@Getter
@Setter
@Named
@SessionScope
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String page = "main";

	public String getPagePrettyPrinted() {
		return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(page)), ' ');
	}

}
