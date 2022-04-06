package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	private static final Logger logger = LogManager.getLogger("HomeController");

	/**
	 * Home page view
	 *
	 * @param model Model Interface
	 * @return home Home page view
	 */
	@RequestMapping("/")
	public String home(Model model)
	{
		logger.info("Get home page");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		logger.info("Admin access");
		return "redirect:/bidList/list";
	}
}
