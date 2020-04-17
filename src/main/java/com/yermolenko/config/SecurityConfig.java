//package com.yermolenko.config;
//
//import com.yermolenko.security.AuthProviderImpl;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@ComponentScan("com.yermolenko.security")
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final AuthProviderImpl authProvider;
//
//    public SecurityConfig(AuthProviderImpl authProvider) {
//        this.authProvider = authProvider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login", "/sign_up").anonymous()
//                .antMatchers("/tours").authenticated()
//                .and().csrf().disable()
//                .formLogin().loginPage("/login").loginProcessingUrl("/login/process")
//                .usernameParameter("email").passwordParameter("password")
//                .and().logout();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider);
//    }
//}
