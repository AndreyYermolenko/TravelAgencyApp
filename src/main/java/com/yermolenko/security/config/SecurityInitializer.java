package com.yermolenko.security.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Security Initialize.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Order(2)
public class SecurityInitializer
        extends AbstractSecurityWebApplicationInitializer {
}
