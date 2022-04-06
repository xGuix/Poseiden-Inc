package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Class Access User Detail Service
 */
@Service
public class AccessUserDetailService implements UserDetailsService
{
    private static final Logger logger = LogManager.getLogger("AccessUserDetailServiceLog");

    @Autowired
    UserRepository userRepository;

    /**
     * Constructor user service
     *
     * @param userRepository User repository
     */
    public AccessUserDetailService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    /**
     * Method who load a UserDetails user from a username
     * @Param  username (String)
     * @Return  user (UserDetails)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username);

        if (user == null)
        {
            logger.info("User not found: {}", username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        logger.info("Found User: {}", user);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}