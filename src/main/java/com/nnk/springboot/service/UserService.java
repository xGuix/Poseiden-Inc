package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService class
 */
@Service
public class UserService
{
    private static final Logger logger = LogManager.getLogger("UserServiceLog");

    /**
     *  Load User Repository
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Constructor user service
     *
     * @param userRepository User repository
     */
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    /**
     * Load User details
     *
     * @param username String username
     * @return UserDetails User details
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), null );
    }

    /**
     * Find users with username :
     * Call to find user in repository
     *
     * @Param  username String Username
     * @Return  User Username
     */
    public User findByUsername(String username)
    {
        logger.info("Search user for username : {}", username);
        return userRepository.findByUsername(username);
    }

    /**
     * Add new user :
     * Save user, encrypt password and give USER role
     *
     * @Param  User Username
     * @Return  User User save
     */
    public User addUser(User user)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        logger.info("Add user for username : {}", user.getUsername());
        return userRepository.save(user);
    }

    /**
     * Verify existing User :
     * Check if user email already exists in database
     *
     * @return Message Information string
     */
    public String validateUser(User user)
    {
        String message = "Not Found";
        User userToFind = findByUsername(user.getUsername());
        if(userToFind !=null)
        {
            message = "Username already used!";
        }
        return message;
    }
}