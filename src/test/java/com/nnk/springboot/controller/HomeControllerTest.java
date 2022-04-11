package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.HomeController;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest
{
    @MockBean
    AccessUserDetailService accessUserDetailService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void HomeTestNoLog() throws Exception
    {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username="xGuix")
    void HomeTestUserLog() throws Exception
    {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl(null));
    }

    @Test
    @WithMockUser(username="xGuix")
    void adminHomeTest() throws Exception
    {
        mockMvc.perform(get("/admin/home"))
                .andExpect(redirectedUrl("/bidList/list"));
    }
}