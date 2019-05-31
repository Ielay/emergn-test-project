package org.emergn.emergntestproject.configuration;

import org.emergn.emergntestproject.controller.RestUserController;
import org.emergn.emergntestproject.controller.UserController;
import org.emergn.emergntestproject.repository.InMemoryUserRepository;
import org.emergn.emergntestproject.repository.UserRepository;
import org.emergn.emergntestproject.security.service.UserDetailsServiceImpl;
import org.emergn.emergntestproject.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserConfiguration {

    @Bean("RestUserController")
    public UserController restUserController(@Qualifier("UserServiceImpl") UserService userService) {
        UserController userController = new RestUserController(userService/*, securityService*/);

        return userController;
    }

    @Bean("UserDetailsServiceImpl")
    public UserDetailsService userDetailsServiceImpl(@Qualifier("InMemoryUserRepository") UserRepository userRepository) {
        UserDetailsService userDetailsService = new UserDetailsServiceImpl(userRepository);

        return userDetailsService;
    }

    @Bean("UserServiceImpl")
    public UserService userServiceImpl(@Qualifier("InMemoryUserRepository") UserRepository userRepository) {
        UserService userService = new UserServiceImpl(userRepository);

        return userService;
    }

    @Bean("InMemoryUserRepository")
    public UserRepository inMemoryUserRepository() {
        UserRepository userRepository = new InMemoryUserRepository();

        return userRepository;
    }
}
