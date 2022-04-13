package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.AccessUserDetailService;
import com.nnk.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class userControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccessUserDetailService accessUserDetailService;

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    User user;
    User newUser;
    List<User> findAll;

    @BeforeEach
    void setupTest()
    {
        findAll = new ArrayList<>(Arrays.asList(new User(1,"xGuix","Admin!123","Guix Debrens","ADMIN"),
                new User(2,"BOB51","Bob51!123","Bob Lazar","ADMIN")));
        user = new User(3,"BOB-MUSIC","Ragga!123","Bob Marley","ADMIN");
        newUser = new User(4,"userTest","TEST!123","fullNameTest","ADMIN");
    }

    @Test
    void homeTest() throws Exception
    {
        when(userRepository.findAll()).thenReturn(findAll);

        mockMvc.perform(get("/user/list")
                        .with(user("admin")
                                .roles("USER","ADMIN")
                                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", findAll))
                .andExpect(view().name("user/list"));
    }

    @Test
    void validateTest() throws Exception
    {
        mockMvc.perform(post("/user/validate")
                        .with(user("xGuix")
                                .roles("ADMIN")
                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .param("username","userTest")
                .param("password", "TEST!123")
                .param("fullname","fullNameTest")
                .param("role","ADMIN"))
                .andExpect(status().isForbidden())
                .andExpect(redirectedUrl(null));
    }

    @Test
    void addUserTest() throws Exception
    {
        mockMvc.perform(get("/user/add")
                        .with(user("admin")
                                .roles("USER","ADMIN")
                                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    void showUpdateFormTest() throws Exception
    {
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));

        mockMvc.perform(get("/user/update/1")
                        .with(user("admin")
                                .roles("USER","ADMIN")
                                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.model().attribute("user", user))
                .andExpect(view().name("user/update"));
    }

    @Test
    void updateUserTest() throws Exception
    {
        when(userRepository.save(user)).thenReturn(user);

        mockMvc.perform(post("/user/update/1")
                .with(user("admin")
                        .roles("USER","ADMIN")
                        .authorities(new SimpleGrantedAuthority("ADMIN")))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username","username")
                .param("password","user")
                .param("fullname","full name")
                .param("role","ADMIN")
                .with(csrf()))
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    void deleteUserTest() throws Exception
    {
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        when(userRepository.findAll()).thenReturn(findAll);
        Mockito.doNothing().when(userRepository).delete(user);

        mockMvc.perform(get("/user/delete/1")
                        .with(user("admin")
                                .roles("USER","ADMIN")
                                .authorities(new SimpleGrantedAuthority("ADMIN"))))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/list"));
    }
}