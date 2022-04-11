package com.nnk.springboot.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * PasswordEncoder Test
 */
@SpringBootTest
public class PasswordEncodeTestIT
{
    @Test
    public void testPassword()
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode("123456");
        System.out.println("[ "+ pw + " ]");
    }
}
