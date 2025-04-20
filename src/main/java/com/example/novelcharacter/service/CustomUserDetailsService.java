package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.CustomUserDetails;
import com.example.novelcharacter.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServiceImpl userService;

    @Autowired
    public CustomUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userData = userService.getUserById(username);

        if (userData != null) {
            return new CustomUserDetails(userData);
        }

        throw new UsernameNotFoundException(username + " not found");
    }
}
