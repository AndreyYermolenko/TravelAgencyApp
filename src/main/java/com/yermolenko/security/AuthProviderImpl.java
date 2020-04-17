//package com.yermolenko.security;
//
//import com.yermolenko.model.Role;
//import com.yermolenko.model.User;
//import com.yermolenko.services.UserService;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class AuthProviderImpl implements AuthenticationProvider {
//
//    private final UserService userService;
//
//    public AuthProviderImpl(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        User user = userService.findUserByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        String password = authentication.getCredentials().toString();
//        if (!password.equals(user.getPassword())) {
//            throw new BadCredentialsException("Password is incorrect");
//        }
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        for (Role role: user.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new UsernamePasswordAuthenticationToken(user, null, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return aClass.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//}
