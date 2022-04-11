package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest
{
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    User userTest;

    @BeforeEach
    void setupTest()
    {
        userService.setUserRepository(userRepository);
        userTest = new User(1,"xGuix","Admin!123","Guix Debrens","ADMIN");
    }

    @Test
    void findByUsernameTest()
    {
        String username = "xGuix";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(userTest);

        assertEquals(userTest,userService.findByUsername(username));
    }

    @Test
    void passwordValidatorFalseTest()
    {
        String password = "No valid";
        Boolean expected = false;
        Boolean result ;
        result = userService.validatePassword(password);

        assertEquals(expected, result);
    }

    @Test
    void passwordValidatorTrueTest()
    {
        String password = "Test-12345";
        Boolean expected = true;
        Boolean result ;
        result = userService.validatePassword(password);

        assertEquals(expected, result);
    }

    @Test
    void userValidatorTrueTest()
    {
        String expected = "Not Found";
        String result;
        result = userService.validateUser(userTest);

        assertEquals(expected, result);
    }

    @Test
    void userValidatorFalseTest()
    {
        String username ="xGuix";
        String expected = "Username already used!";
        String result;
        Mockito.when(userRepository.findByUsername(username)).thenReturn(userTest);
        result = userService.validateUser(userTest);

        assertEquals(expected, result);
    }

    @Test
    void addTest()
    {
        Mockito.when(userRepository.save(userTest)).thenReturn(userTest);
        userService.addUser(userTest);

        verify(userRepository,Mockito.times(1)).save(userTest);
    }
}