package com.yermolenko.security.provider;

import com.yermolenko.dao.TokenDAO;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.Token;
import com.yermolenko.model.User;
import com.yermolenko.security.token.TokenAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenDAO tokenDAO;

    private final UserDAO userDAO;

    private final UserDetailsService userDetailsService;



    public TokenAuthenticationProvider(TokenDAO tokenDAO, UserDAO userDAO, UserDetailsService userDetailsService) {
        this.tokenDAO = tokenDAO;
        this.userDAO = userDAO;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication)authentication;

        Token tokenCandidate = tokenDAO.findOneByValue(tokenAuthentication.getName());

        if (tokenCandidate != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenCandidate.getUser().getEmail());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else throw new IllegalArgumentException("Bad token");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
