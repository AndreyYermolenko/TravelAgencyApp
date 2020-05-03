package com.yermolenko.security.filters;

import com.yermolenko.security.token.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * TokenAuthFilter
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Component
public class TokenAuthFilter implements Filter {
    /**
     * Method init.
     *
     * @param filterConfig of type FilterConfig
     * @throws ServletException when
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Method doFilter.
     *
     * @param servletRequest of type ServletRequest
     * @param servletResponse of type ServletResponse
     * @param filterChain of type FilterChain
     * @throws IOException when
     * @throws ServletException when
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        String token = request.getParameter("token");

        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        if (token == null) {
            tokenAuthentication.setAuthenticated(false);
        } else {
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Method destroy.
     */
    @Override
    public void destroy() {

    }
}
