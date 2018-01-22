package org.philmaster.boot.session;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Philmasteryeah
 * 
 *         switch the content pages with this bean
 * 
 *         its very important to use SessionScope here otherwise it will be
 *         reset to 'main 'after menu click
 * 
 *         getPage() will return the name of the current page
 *         getPagePrettyPrinted() user readable printed for info messages
 *
 */

@Getter
@Setter
@Named
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String page = "main";

	@PostConstruct
	void init() {

		System.err.print("session created");
	}

	public String getPagePrettyPrinted() {
		return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(page)), ' ');
	}

}
