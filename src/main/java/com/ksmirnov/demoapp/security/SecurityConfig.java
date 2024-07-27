package com.ksmirnov.demoapp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Value("${app.authentication.type}")
    private String authenticationType;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        if (authenticationType.equals(AuthenticationType.MEMORY.name())) {
            UserDetails user1 = User.builder()
                    .username("john")
                    .password("{noop}test123")
                    .roles("EMPLOYEE")
                    .build();

            UserDetails user2 = User.builder()
                    .username("mary")
                    .password("{noop}test123")
                    .roles("EMPLOYEE", "MANAGER")
                    .build();

            UserDetails user3 = User.builder()
                    .username("susan")
                    .password("{noop}test123")
                    .roles("EMPLOYEE", "MANAGER", "ADMIN")
                    .build();

            return new InMemoryUserDetailsManager(user1, user2, user3);
        } else if (authenticationType.equals(AuthenticationType.DEFAULT_TABLE.name())) {
            return new JdbcUserDetailsManager(dataSource);
        } else if (authenticationType.equals(AuthenticationType.CUSTOM_TABLE.name())){
            JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

            userDetailsManager.setUsersByUsernameQuery(
                    "select user_id, pw, active from members where user_id=?"
            );
            userDetailsManager.setAuthoritiesByUsernameQuery(
                    "select user_id, role from roles where user_id=?"
            );

            return userDetailsManager;
        } else {
            return null;
        }
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // use HTTP basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // disable Cross-Site Request Forgery
        // generally, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

    public enum AuthenticationType {
        MEMORY,
        DEFAULT_TABLE,
        CUSTOM_TABLE
    }
}
