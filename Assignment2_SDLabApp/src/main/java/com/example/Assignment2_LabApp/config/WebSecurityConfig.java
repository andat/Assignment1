package com.example.Assignment2_LabApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.authenticationProvider(customAuthenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/students").hasAnyRole("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.POST,"/students").hasRole("TEACHER")
                .antMatchers(HttpMethod.DELETE,"/students").hasRole("TEACHER")
                .antMatchers(HttpMethod.PUT,"/students").hasRole("TEACHER")

                .antMatchers(HttpMethod.POST, "/laboratories").hasRole("TEACHER")
                .antMatchers(HttpMethod.PUT, "/laboratories/**").hasRole("TEACHER")
                .antMatchers(HttpMethod.DELETE, "/laboratories/**").hasRole("TEACHER")
                .antMatchers(HttpMethod.GET, "/laboratories").hasAnyRole("TEACHER", "STUDENT")

                .antMatchers(HttpMethod.GET,"/assignments").hasAnyRole("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.POST,"/assignments").hasRole("TEACHER")
                .antMatchers(HttpMethod.PUT,"/assignments").hasRole("TEACHER")
                .antMatchers(HttpMethod.DELETE,"/assignments").hasRole("TEACHER")
                .antMatchers(HttpMethod.GET,"/attendance").hasRole("TEACHER")
                .antMatchers(HttpMethod.GET,"/attendance/students/**").hasAnyRole("TEACHER", "STUDENT")
                .antMatchers(HttpMethod.POST,"/attendance").hasRole("TEACHER")
                .antMatchers(HttpMethod.PUT,"/attendance").hasRole("TEACHER")
                .antMatchers(HttpMethod.DELETE,"/attendance").hasRole("TEACHER")
                .antMatchers("/submissions").hasAnyRole("TEACHER", "STUDENT")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();

    }
}
