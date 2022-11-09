package com.infenion.ccmalogic.services;

import com.infenion.ccmamodel.model.Requester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
@Configuration
public class RequesterDetailService implements UserDetailsService {
    @Autowired
    RequesterService requesterService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Requester requester = requesterService.findByUserName(username);
        if(requester == null) throw new UsernameNotFoundException("User Not found");
        UserDetails user = User.withUsername(requester.getUserName())
                .password(requester.getPassword()).authorities("REQUESTER")
                .build();

        return user;
    }
}
