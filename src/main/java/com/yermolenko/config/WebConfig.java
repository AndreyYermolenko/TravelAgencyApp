package com.yermolenko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Class WebConfig customizes web config.
 *
 * @author Andrey
 * Created on 02.05.2020
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan("com.yermolenko")
public class WebConfig implements WebMvcConfigurer {

    /**
     * Method viewResolver customizes view resolver.
     * @return InternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/pages/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    /**
     * Method addResourceHandlers for add resources handler.
     *
     * @param registry of type ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}
