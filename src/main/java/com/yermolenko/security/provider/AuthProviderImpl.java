package com.yermolenko.security.provider;

import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.Role;
import com.yermolenko.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * AuthProvider.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserDAO userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;


    /**
     * Constructor AuthProviderImpl creates a new AuthProviderImpl instance.
     *
     * @param userRepository of type UserDAO
     * @param bCryptPasswordEncoder
     */
    public AuthProviderImpl(UserDAO userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    /**
     * Method supports.
     *
     * @param aClass of type Class<?>
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}