package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.SignupController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.AccessUserDetailService;
import com.nnk.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SignupController.class)
public class SignupControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccessUserDetailService accessUserDetailService;

    @MockBean
    UserService userService;

    String err;
    User userSetup ;

    @BeforeEach
    void setup()
    {
        err = "Not Found";
        userSetup = new User(1,"xGuix","Admin!123","Guix Debrens","ADMIN");
    }

    @Test
    void getSignUpViewReturnSignupPage() throws Exception
    {
        mockMvc.perform(get("/signup")
                        .param("username","xGuix")
                        .param("password","Admin!123")
                        .param("fullname","Guix Debrens")
                        .param("model", "user"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("user"))
                .andReturn();
    }

    @Test
    void postSignUpWhenReturnError() throws Exception
    {
        mockMvc.perform(post("/signup")
                        .param("model", "user"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeCount(1))
                .andExpect(model().attributeHasErrors())
                .andExpect(redirectedUrl("/signup"))
                .andReturn();
    }

    @Test
    void postSignUpWhenReturnErrorWrongPasswordFormat() throws Exception
    {
        Mockito.when(userService.validateUser(userSetup)).thenReturn(err);
        Mockito.when(userService.validatePassword("user")).thenReturn(false);
        Mockito.when(userService.addUser(userSetup)).thenReturn(userSetup);

        mockMvc.perform(post("/signup"))
                .andExpect(status().isFound())
                .andExpect(flash().attributeCount(1))
                .andExpect(model().attributeHasErrors())
                .andExpect(redirectedUrl("/signup"))
                .andReturn();
    }
}