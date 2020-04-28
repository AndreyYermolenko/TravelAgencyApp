package com.yermolenko.security.config;

import com.yermolenko.security.filters.TokenAuthFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@ComponentScan("com.yermolenko")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(3)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;

    private final TokenAuthFilter tokenAuthFilter;

    public RestSecurityConfig(@Qualifier("tokenAuthenticationProvider") AuthenticationProvider authenticationProvider, TokenAuthFilter tokenAuthFilter) {
        this.authenticationProvider = authenticationProvider;
        this.tokenAuthFilter = tokenAuthFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
                .antMatcher("/api/**")
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers("/api/sign_up").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/tours").hasAuthority("user")
                .antMatchers("/api/reservationTour").hasAuthority("user");
        http.csrf().disable();
    }
}
