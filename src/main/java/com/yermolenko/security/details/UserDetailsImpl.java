package com.yermolenko.security.details;

import com.yermolenko.model.Role;
import com.yermolenko.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User Details.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public class UserDetailsImpl implements UserDetails {

    private User user;

    /**
     * Constructor UserDetailsImpl creates a new UserDetailsImpl instance.
     *
     * @param user of type User
     */
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    /**
     * Method getAuthorities returns the authorities of this UserDetailsImpl object.
     *
     *
     *
     * @return the authorities (type Collection<? extends GrantedAuthority>) of this UserDetailsImpl object.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * Method getPassword returns the password of this UserDetailsImpl object.
     *
     *
     *
     * @return the password (type String) of this UserDetailsImpl object.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Method getUsername returns the username of this UserDetailsImpl object.
     *
     *
     *
     * @return the username (type String) of this UserDetailsImpl object.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Method isAccountNonExpired returns the accountNonExpired of this UserDetailsImpl object.
     *
     *
     *
     * @return the accountNonExpired (type boolean) of this UserDetailsImpl object.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Method isAccountNonLocked returns the accountNonLocked of this UserDetailsImpl object.
     *
     *
     *
     * @return the accountNonLocked (type boolean) of this UserDetailsImpl object.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Method isCredentialsNonExpired returns the credentialsNonExpired of this UserDetailsImpl object.
     *
     *
     *
     * @return the credentialsNonExpired (type boolean) of this UserDetailsImpl object.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Method isEnabled returns the enabled of this UserDetailsImpl object.
     *
     *
     *
     * @return the enabled (type boolean) of this UserDetailsImpl object.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Method getUser returns the user of this UserDetailsImpl object.
     *
     *
     *
     * @return the user (type User) of this UserDetailsImpl object.
     */
    public User getUser() {
        return user;
    }
}
