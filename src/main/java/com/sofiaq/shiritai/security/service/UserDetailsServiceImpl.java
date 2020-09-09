package com.sofiaq.shiritai.security.service;

import com.sofiaq.shiritai.security.model.User;
import com.sofiaq.shiritai.security.model.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username).get();
        return UserMain.build(user);
    }
    
}
