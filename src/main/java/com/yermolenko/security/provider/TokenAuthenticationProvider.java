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

/**
 * TokenAuthenticationProvider.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenDAO tokenDAO;

    private final UserDetailsService userDetailsService;



    /**
     * Constructor TokenAuthenticationProvider creates a new TokenAuthenticationProvider instance.
     *
     * @param tokenDAO of type TokenDAO
     * @param userDetailsService of type UserDetailsService
     */
    public TokenAuthenticationProvider(TokenDAO tokenDAO, UserDetailsService userDetailsService) {
        this.tokenDAO = tokenDAO;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Method authenticate.
     *
     * @param authentication of type Authentication
     * @return Authentication
     * @throws AuthenticationException when
     */
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

    /**
     * Method supports.
     *
     * @param authentication of type Class<?>
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
