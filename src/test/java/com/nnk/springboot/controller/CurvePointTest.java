package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.AccessUserDetailService;
import com.nnk.springboot.service.CurveService;
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
@WebMvcTest(CurveController.class)
@WithMockUser(username = "xGuix")
public class CurvePointTest
{
    @MockBean
    AccessUserDetailService accessUserDetailService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CurveService curveService;

    CurvePoint curvePoint;
    List<CurvePoint> findAll;

    @BeforeEach
    void setupTest()
    {
        curvePoint = new CurvePoint(1,10.0,100.0);
        findAll = new ArrayList<>(Arrays.asList(new CurvePoint(1,10.0,100.0),
                new CurvePoint(2,20.0,200.0)));
    }

    @Test
    void homeTest() throws Exception
    {
        Mockito.when(curveService.findAll()).thenReturn(findAll);

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("curveList"))
                .andExpect(MockMvcResultMatchers.model().attribute("curveList", findAll))
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    void addBidFormTest() throws Exception
    {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void validateTest() throws Exception
    {
        Mockito.when(curveService.save(curvePoint)).thenReturn(curvePoint);

        mockMvc.perform(post("/curvePoint/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(curvePoint))
                .with(csrf()))
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception
    {
        Mockito.when(curveService.findById(1)).thenReturn(curvePoint);

        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("curvePoint"))
                .andExpect(MockMvcResultMatchers.model().attribute("curvePoint", curvePoint))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void updateCurveTest() throws Exception
    {
        Mockito.when(curveService.save(curvePoint)).thenReturn(curvePoint);

        mockMvc.perform(post("/curvePoint/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(curvePoint ))
                .with(csrf()))
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void deleteCurvePointTest() throws Exception
    {
        Mockito.doNothing().when(curveService).delete(1);

        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curveService,Mockito.times(1)).delete(1);
    }
}