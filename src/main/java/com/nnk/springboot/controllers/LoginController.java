package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController
{
    private static final Logger logger = LogManager.getLogger("LoginController");

    /**
     *  Load OAuth2AuthorizedClientService
     */
    private final OAuth2AuthorizedClientService authorizedClientService;

    /**
     *  Custom constructor Login OAuth2
     *
     * @param authorizedClientService OAuth2AuthorizedClientService
     */
    public LoginController(OAuth2AuthorizedClientService authorizedClientService)
    {
        this.authorizedClientService = authorizedClientService;
    }

    /**
     * Render ModelAndView login
     *
     * @return login Page "login"
     */
    @GetMapping(value ="/login")
    public String login()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        logger.info("Get login page");
        return "login";
    }

    /**
     * Render the ModelAndView 403
     * Add an Object attribute with an error message
     *
     * @return error Page "403"
     */
    @GetMapping(value ="error")
    public String error()
    {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        logger.info("Error with access");
        return "/403";
    }
}