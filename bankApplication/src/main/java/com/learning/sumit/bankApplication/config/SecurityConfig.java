package com.learning.sumit.bankApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/",
            "/swagger-ui/",
            "/admin/**",
            "/h2-console/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated().and().httpBasic();

        http.headers().frameOptions().disable();//remove this while not using h2 web console
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).
                withUser("111").password("111").roles("USER");
    }
}
