package com.github.mdm.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class MdmWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        // Allow Frontend to be accessed without authentication
        web.ignoring()
                .antMatchers("/socket/**")
                .antMatchers("/index.html", "/", "/login", "/dashboard")
                .antMatchers("/resources/**", "/static/**", "/**/favicon.ico", "/**/*.js", "/**/*.css");
    }

}