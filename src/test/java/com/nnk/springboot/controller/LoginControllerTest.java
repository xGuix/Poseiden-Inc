package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.service.AccessUserDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccessUserDetailService accessUserDetailService;

    @Test
    void getLoginReturnLoginPage() throws Exception
    {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(0))
                .andReturn();
    }

    @Test
    @WithMockUser(username="xGuix")
    void getAccessDeniedReturnError() throws Exception
    {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("/403"))
                .andExpect(model().hasNoErrors())
                .andExpect(model().size(0))
                .andReturn();
    }
}