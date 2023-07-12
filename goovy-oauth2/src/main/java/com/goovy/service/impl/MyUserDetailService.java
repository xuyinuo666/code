package com.goovy.service.impl;

import com.goovy.oauth2.dto.UserDTO;
import com.goovy.service.IUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.getUserByUsername(username);
        return new User(username,user.getPasswd(), AuthorityUtils.createAuthorityList(user.getPasswd()));
    }
}
