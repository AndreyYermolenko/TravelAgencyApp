package com.yermolenko.security.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * TokenAuthentication
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public class TokenAuthentication implements Authentication {

    private String token;
    private boolean isAuthenticated;
    private UserDetails userDetails;


    /**
     * Constructor TokenAuthentication creates a new TokenAuthentication instance.
     *
     * @param token of type String
     */
    public TokenAuthentication(String token) {
        this.token = token;
    }

    /**
     * Method setUserDetails sets the userDetails of this TokenAuthentication object.
     *
     *
     *
     * @param userDetails the userDetails of this TokenAuthentication object.
     *
     */
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * Method getAuthorities returns the authorities of this TokenAuthentication object.
     *
     *
     *
     * @return the authorities (type Collection<? extends GrantedAuthority>) of this TokenAuthentication object.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    /**
     * Method getCredentials returns the credentials of this TokenAuthentication object.
     *
     *
     *
     * @return the credentials (type Object) of this TokenAuthentication object.
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * Method getDetails returns the details of this TokenAuthentication object.
     *
     *
     *
     * @return the details (type Object) of this TokenAuthentication object.
     */
    @Override
    public Object getDetails() {
        return userDetails;
    }

    /**
     * Method getPrincipal returns the principal of this TokenAuthentication object.
     *
     *
     *
     * @return the principal (type Object) of this TokenAuthentication object.
     */
    @Override
    public Object getPrincipal() {
        return null;
    }

    /**
     * Method isAuthenticated returns the authenticated of this TokenAuthentication object.
     *
     *
     *
     * @return the authenticated (type boolean) of this TokenAuthentication object.
     */
    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Method setAuthenticated sets the authenticated of this TokenAuthentication object.
     *
     *
     *
     * @param isAuthenticated the authenticated of this TokenAuthentication object.
     *
     * @throws IllegalArgumentException when
     */
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated  = isAuthenticated;
    }

    /**
     * Method getName returns the name of this TokenAuthentication object.
     *
     *
     *
     * @return the name (type String) of this TokenAuthentication object.
     */
    @Override
    public String getName() {
        return token;
    }
}
