package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

/**
 *  User Thymeleaf controller
 */
@Controller
public class UserController
{
    /**
     *  Load User Service
     */
    @Autowired
    private UserService userService;

    /**
     *  Load User Repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     *  ADMIN Role allowed
     *  CRUD user list not allow for users
     *
     * @param model Model
     * @return user/list User list page
     */
    @RolesAllowed("ADMIN")
    @RequestMapping("/user/list")
    public String home(@NotNull Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    /**
     * Get add user page:
     * Form for new user
     *
     * @param user User add user
     * @return user/add New user page
     */
    @GetMapping("/user/add")
    public String add(User user)
    {
        return "user/add";
    }

    /**
     * Post to validate user:
     * Form for new user
     *
     * @param user User new user
     * @param result BindingResult result
     * @param model Model rating
     * @return user/list All user list page
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, @NotNull BindingResult result, Model model)
    {
        if (!result.hasErrors())
        {
            if(userService.validateUser(user).equals("Not Found"))
            {
                if(userService.validatePassword(user.getPassword()))
                {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    user.setPassword(encoder.encode(user.getPassword()));
                    userRepository.save(user);
                    model.addAttribute("users", userRepository.findAll());
                    return "redirect:/user/list";
                }
                model.addAttribute("passwordError", "Invalid password:" +
                        " it should contain between 8 min and 16 max characters with 1 uppercase, 1 digits and 1 special character");
                return "user/add";
            }
            model.addAttribute("usernameError", "Invalid username already used. Please retry!");
            return "user/add";
        }
        return "user/add";
    }

    /**
     * Get user update:
     * Form to update user
     *
     * @param id Integer id
     * @param model Model user
     * @return user/update Update user page
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, @NotNull Model model)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Post user update:
     * Form to post update user
     *
     * @param id Integer id
     * @param user User update
     * @param result BindingResult result
     * @param model Model user
     * @return redirect:/user/list Update user list page
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, @NotNull BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "user/update";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    /**
     * Get user to delete:
     * Call to delete user
     *
     * @param id Integer id
     * @param model Model user
     * @return redirect:/user/list Update user list page
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, @NotNull Model model)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}