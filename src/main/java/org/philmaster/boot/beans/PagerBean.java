package org.philmaster.boot.beans;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author Philmasteryeah
 * 
 *         switch the content pages with this bean
 * 
 *         its very important to use SessionScope here otherwise its will be
 *         reset to 'main 'after menu click
 *
 */

@Named
@SessionScope
public class PagerBean {

    private String page = "main";

    public String getPage() {
	return page;
    }

    public void setPage(String page) {
	this.page = page;
    }

    public String getPagePrettyPrinted() {
	return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(page)), ' ');
    }

}
