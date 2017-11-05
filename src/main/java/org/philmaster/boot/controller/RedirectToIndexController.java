package org.philmaster.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * redirects all to index if you are logged in to avoid page not found
 * exceptions
 *
 */
@Controller
public class RedirectToIndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:index.xhtml";
	}
}
