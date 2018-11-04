package org.philmaster.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Philmaster
 * 
 *         redirects all to index if you are logged in to avoid page not found
 *         exceptions
 *
 */
@Controller
public class RedirectToIndexController {

	@GetMapping("/")
	public String redirect() {
		System.err.println("red");
		return "redirect:index.xhtml";
	}

}
