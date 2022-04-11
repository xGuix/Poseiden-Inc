package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.AccessUserDetailService;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RuleNameController.class)
@WithMockUser(username = "xGuix")
public class RuleNameControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccessUserDetailService accessUserDetailService;

    @MockBean
    RuleNameService ruleNameService;

    RuleName ruleName;
    List<RuleName> findAll;

    @BeforeEach
    void setupTest()
    {
        ruleName = new RuleName("nameTest","descTest","jsonTest","tempTest","sqlTest","sql-partTest");
        findAll = new ArrayList<>(Arrays.asList(new RuleName("nameTest","descTest","jsonTest","tempTest", "sqlTest","sql-partTest"),
                new RuleName("nameTest","descTest","jsonTest","tempTest","sqlTest","sql-partTest")));
    }

    @Test
    void homeTest() throws Exception
    {
        Mockito.when(ruleNameService.findAll()).thenReturn(findAll);

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleNameList"))
                .andExpect(MockMvcResultMatchers.model().attribute("ruleNameList", findAll))
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    void addBidFormTest() throws Exception
    {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    void validateTest() throws Exception
    {
        Mockito.when(ruleNameService.save(ruleName)).thenReturn(ruleName);

        mockMvc.perform(post("/ruleName/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(ruleName))
                .with(csrf()))
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception
    {
        Mockito.when(ruleNameService.findById(1)).thenReturn(ruleName);

        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleName"))
                .andExpect(MockMvcResultMatchers.model().attribute("ruleName", ruleName))
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    void updateRuleNameTest() throws Exception
    {
        Mockito.when(ruleNameService.save(ruleName)).thenReturn(ruleName);

        mockMvc.perform(post("/ruleName/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(ruleName)).with(csrf()))
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void deleteRuleNameTest() throws Exception
    {
        Mockito.doNothing().when(ruleNameService).delete(1);

        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(redirectedUrl("/ruleName/list"));
        verify(ruleNameService,Mockito.times(1)).delete(1);
    }
}