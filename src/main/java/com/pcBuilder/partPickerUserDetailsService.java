package com.pcBuilder;

import com.pcBuilder.daos.UserDao;
import com.pcBuilder.models.User;

import eu.fraho.spring.securityJwt.base.dto.JwtUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to load users for auth
 */
@Component
public class partPickerUserDetailsService implements UserDetailsService {
    /**
     * The Dao used to access user data
     */
    private UserDao userDao;

    /**
     * Create a new instance of this class
     */
    public partPickerUserDetailsService(UserDao userDao){
        this.userDao = userDao;
    }


    /**
     * Loads a user by there username
     * @param username the username to load
     * @return the user details as a JwtUser
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUser(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        JwtUser jwtUser = new JwtUser();

        jwtUser.setUsername(user.getUsername());
        jwtUser.setPassword(user.getPassword());

        List<String> roles = userDao.getRolesForUser(username);
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        jwtUser.setAuthorities(authorities);
        jwtUser.setAccountNonExpired(true);
        jwtUser.setAccountNonLocked(true);
        jwtUser.setApiAccessAllowed(true);
        jwtUser.setCredentialsNonExpired(true);
        jwtUser.setEnabled(true);

        return jwtUser;
    }
}
