package com.tdt.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tdt.filter.JJWTFilter;
import com.tdt.service.TokenAuthenticationService;
import com.tdt.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = Logger.getLogger(SpringSecurityConfig.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    
//    @Autowired
//    private CorsFilter corsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        
        http.csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("*/*"));
        http
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .headers().cacheControl();

        http
                //.addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()// allow for static resources
                .antMatchers("/signup").permitAll()
                .antMatchers("/forgot").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/reset").permitAll()
                .antMatchers("/health").permitAll()
                .antMatchers("/hello").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/reset_pw").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new JJWTFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class);


    }
}
