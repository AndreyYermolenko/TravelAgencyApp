package com.yermolenko.config;

import com.yermolenko.security.config.MvcSecurityConfig;
import com.yermolenko.security.config.RestSecurityConfig;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Class WebAppInitializer for app initialization.
 *
 * @author Andrey
 * Created on 02.05.2020
 */
@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Method getRootConfigClasses returns the rootConfigClasses of this WebAppInitializer object.
     *
     *
     *
     * @return the rootConfigClasses (type Class<?>[]) of this WebAppInitializer object.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                MvcSecurityConfig.class,
                RestSecurityConfig.class
        };
    }

    /**
     * Method getServletConfigClasses returns the servletConfigClasses of this WebAppInitializer object.
     *
     *
     *
     * @return the servletConfigClasses (type Class<?>[]) of this WebAppInitializer object.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
                WebConfig.class
        };
    }

    /**
     * Method getServletMappings returns the servletMappings of this WebAppInitializer object.
     *
     *
     *
     * @return the servletMappings (type String[]) of this WebAppInitializer object.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
