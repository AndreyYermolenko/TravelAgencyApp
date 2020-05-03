package com.yermolenko.security.config;

import com.yermolenko.security.provider.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Class MvcSecurityConfig is custom config for mvc security.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@EnableWebSecurity
@ComponentScan("com.yermolenko")
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(4)
public class MvcSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;

    /**
     * Constructor MvcSecurityConfig creates a new MvcSecurityConfig instance.
     *
     * @param authProvider of type AuthProviderImpl
     */
    public MvcSecurityConfig(@Qualifier("authProviderImpl") AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    /**
     * Configure.
     *
     * @param http of type HttpSecurity
     * @throws Exception when
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/", "/sign_up").anonymous()
                .and()
            .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/tours/quickSearch")
                .failureForwardUrl("/login?error=true")
                .loginPage("/login")
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }

    /**
     * Configure.
     *
     * @param auth of type AuthenticationManagerBuilder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

}
