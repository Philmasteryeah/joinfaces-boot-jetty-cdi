package org.philmaster.boot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Configuration
@ConfigurationProperties("menu")
public class MenuProperties {

	private List<SubMenuItem> items = new ArrayList<>();

	@Getter
	@Setter
	public static class SubMenuItem {

		private String submenu;
		private List<MenuItem> subitems = new ArrayList<>();
	}

	@Getter
	@Setter
	public static class MenuItem {

		private String title;
		private String pageName;
		private String icon;
		private boolean isAjax;
	}

}
