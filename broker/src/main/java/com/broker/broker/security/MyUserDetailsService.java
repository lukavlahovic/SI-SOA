package com.broker.broker.security;

import com.broker.broker.domain.Role;
import com.broker.broker.domain.UserBroker;
import com.broker.broker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserBroker userBroker = userRepository.findByUsername(s);

        if (userBroker == null) throw new UsernameNotFoundException(s);

        return new User(userBroker.getUsername(), userBroker.getPassword(), getAuthorities(userBroker));
    }

    //util
    private Collection<? extends GrantedAuthority> getAuthorities(UserBroker userBroker) {

        List<SimpleGrantedAuthority> authoritiesForUser = new ArrayList<>();


        //ROLE_ADMIN, ROLE_CUSTOMER
        for (Role r : userBroker.getRoles()){
            authoritiesForUser.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
        }

        return authoritiesForUser;
    }
}
