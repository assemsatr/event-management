package com.SimpleEventMaster.awesomeAPP.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    private static final String[] PUBLIC_PATH ={
            "/users/registration",
            "/events/findAll","/events/findById/*","/events/findAllPaginated/**/*","/events/findAllFilter/**/*",
            "/eventParticipant/event/*",

            "/users/findAll","/users/findById/*",
            "/users/delete/*","/users/approve/*","/users/disapprove/*",
            "/events/addOrUpdateEvent", "/events/delete/*","/role/make-admin/*"
    };
    private static final String[] ADMIN_PATH = {
//            "/users/findAll","/users/findById/*",
//    "/users/delete/*","/users/approve/*","/users/disapprove/*",
//            "/events/addOrUpdateEvent", "/events/delete/*","/role/make-admin/*"
    };

    private final DataSource securityDataSource;

   @Autowired
    public SecurityConfig(DataSource securityDataSource) {
        this.securityDataSource = securityDataSource;
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(securityDataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, name as role_name from user_role where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers(PUBLIC_PATH).permitAll()
                .antMatchers(ADMIN_PATH).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
