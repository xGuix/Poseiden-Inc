package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 *  Signup Thymeleaf controller
 */
@Controller
public class SignupController
{
    private static final Logger logger = LogManager.getLogger("SignupControllerLog");

    private final UserService userService;

    /**
     *  Constructor friend controller
     *
     *  @param userService User service
     */
    public SignupController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     *  Get signup page:
     * 	- user model
     *
     *  @param model Model
     *
     *  @return signup Signup page url
     */
    @GetMapping(value = "/signup")
    public String signUpView(@NotNull Model model)
    {
        model.addAttribute("user", new User());
        logger.info("Get signup page");
        return "signup";
    }

    /**
     *  Post signup form:
     * 	- create a new user
     *
     *  @param user SignupDTO
     *  @param result BindingResult
     *  @param model Model
     *  @param redirAttrs RedirectAttributes
     *
     *  @return signup Signup page url
     */
    @PostMapping(value = "/signup")
    public String signUp(@Valid User user, BindingResult result, Model model, RedirectAttributes redirAttrs)
    {
        String err = userService.validateUser(user);
        if (!"Not Found".equals(err))
        {
            ObjectError error = new ObjectError("globalError", err);
            result.addError(error);
        }
        if (result.hasErrors())
        {
            redirAttrs.addFlashAttribute("error", err);
            logger.info("Data error, no signup");
            return "redirect:/signup";
        }
        if(userService.validatePassword(user.getPassword()))
        {
            logger.info("Valid password");
            userService.addUser(user);
            redirAttrs.addFlashAttribute("success", "Success!");
            logger.info("New user has been saved: {}", user);
            return "redirect:/login";
        }
        redirAttrs.addFlashAttribute("error","Invalid password:" +
                " it should contain between 8 min and 16 max characters" +
                " with 1 uppercase, 1 digits and 1 special character");
        logger.info("Invalid password, user not save: {}", user);
        return "redirect:/signup";
    }
}