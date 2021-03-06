package com.noter.api.users.service;

import com.noter.api.users.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserDao dao;

    @Autowired
    public UserService(@Qualifier("fake") final UserDao dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return dao
            .getUserByName(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }
}
