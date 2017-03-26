package org.philmaster.boot.beans;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
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
 */

@Getter
@Setter
@Named
@SessionScope
public class PagerBean {

    private String page = "main";

    public String getPagePrettyPrinted() {
	return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(page)), ' ');
    }

}
