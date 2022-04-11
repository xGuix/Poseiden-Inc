package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.AccessUserDetailService;
import com.nnk.springboot.service.RatingService;
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
@WebMvcTest(RatingController.class)
@WithMockUser(username = "xGuix")
public class RatingControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccessUserDetailService accessUserDetailService;

    @MockBean
    RatingService ratingService;

    Rating rating;
    List<Rating> findAll;

    @BeforeEach
    void setupTest()
    {
        rating = new Rating("MoodyTest","sandPTest","fitchRatingTest", 10);
        findAll = new ArrayList<>(Arrays.asList(new Rating("MoodyTest","sandPTest","fitchRatingTest", 10),
                new Rating("Moody2Test","sandP2Test","fitchRating2Test",11)));
    }

    @Test
    void homeTest() throws Exception
    {
        Mockito.when(ratingService.findAll()).thenReturn(findAll);

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("ratingList"))
                .andExpect(MockMvcResultMatchers.model().attribute("ratingList", findAll))
                .andExpect(view().name("rating/list"));
    }

    @Test
    void addBidFormTest() throws Exception
    {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    void validateTest() throws Exception
    {
        Mockito.when(ratingService.save(rating)).thenReturn(rating);

        mockMvc.perform(post("/rating/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(rating))
                .with(csrf()))
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        Mockito.when(ratingService.findById(1)).thenReturn(rating);

        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("rating"))
                .andExpect(MockMvcResultMatchers.model().attribute("rating", rating))
                .andExpect(view().name("rating/update"));
    }

    @Test
    void updateBidTest() throws Exception
    {
        Mockito.when(ratingService.save(rating)).thenReturn(rating);

        mockMvc.perform(post("/rating/update/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(String.valueOf(rating))
                .with(csrf()))
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void deleteRatingTest() throws Exception
    {
        Mockito.doNothing().when(ratingService).delete(1);

        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(redirectedUrl("/rating/list"));
        verify(ratingService,Mockito.times(1)).delete(1);
    }
}