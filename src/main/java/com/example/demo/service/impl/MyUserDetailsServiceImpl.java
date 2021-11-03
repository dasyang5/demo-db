package com.example.demo.service.impl;

import com.example.demo.bean.UserDetail;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Alex
 * @date 2021/11/2 16:09
 */
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not exist.");
        }

        UserDetail userDetail = new UserDetail();
        userDetail.setUser(user);

        userDetail.setRoles(new ArrayList<>());

        return userDetail;
    }
}
