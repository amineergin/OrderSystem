package com.example.qkare.CustomerOrders.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/customer/***").hasRole("CUSTOMER")
                .requestMatchers("/api/product/***").hasRole("ADMIN")
                .requestMatchers("/api/order/***").hasRole("CUSTOMER")
                .requestMatchers("api/***").hasRole("ADMIN")
                .requestMatchers("api/favorites/***").hasRole("CUSTOMER").anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());

        http.sessionManagement(
                session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails customer = User.withDefaultPasswordEncoder()
                .username("customer")
                .password("customer")
                .roles("CUSTOMER") // Rol
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN") // Rol
                .build();

        UserDetails seller = User.withDefaultPasswordEncoder()
                .username("seller")
                .password("seller")
                .roles("SELLER") // Rol
                .build();

        return new InMemoryUserDetailsManager(customer, admin, seller);
    }
}
