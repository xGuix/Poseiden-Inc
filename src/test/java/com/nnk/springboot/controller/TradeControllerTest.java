package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.AccessUserDetailService;
import com.nnk.springboot.service.TradeService;
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
@WebMvcTest(TradeController.class)
@WithMockUser(username = "xGuix")
public class TradeControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccessUserDetailService accessUserDetailService;

    @MockBean
    TradeService tradeService;

    Trade trade;
    List<Trade> findAll;

    @BeforeEach
    void setupTest()
    {
        trade = new Trade("accountTest", "typeTest", 100d);
        findAll = new ArrayList<>(Arrays.asList(new Trade("account1Test", "type1Test", 100d),
                new Trade("account2Test", "type2Test", 200d)));
    }

    @Test
    void homeTest() throws Exception
    {
        Mockito.when(tradeService.findAll()).thenReturn(findAll);

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("tradeList"))
                .andExpect(MockMvcResultMatchers.model().attribute("tradeList", findAll))
                .andExpect(view().name("trade/list"));
    }

    @Test
    void addBidFormTest() throws Exception
    {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    void validateTest() throws Exception
    {
        Mockito.when(tradeService.save(trade)).thenReturn(trade);
        //Then
        mockMvc.perform(post("/trade/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(trade))
                .with(csrf()))
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception
    {
        Mockito.when(tradeService.findById(1)).thenReturn(trade);

        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("trade"))
                .andExpect(MockMvcResultMatchers.model().attribute("trade", trade))
                .andExpect(view().name("trade/update"));
    }

    @Test
    void updateTradeTest() throws Exception
    {
        Mockito.when(tradeService.save(trade)).thenReturn(trade);

        mockMvc.perform(post("/trade/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(trade))
                .with(csrf()))
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void deleteTradeTest() throws Exception
    {
        Mockito.doNothing().when(tradeService).delete(1);

        mockMvc.perform(get("/trade/delete/1")).andExpect(redirectedUrl("/trade/list"));
        verify(tradeService,Mockito.times(1)).delete(1);
    }
}