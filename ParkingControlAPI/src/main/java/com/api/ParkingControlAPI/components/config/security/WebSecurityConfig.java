package com.api.ParkingControlAPI.components.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/parking-control/user").permitAll()

                        .requestMatchers(HttpMethod.GET, "/parking-control/user/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/parking-control/user/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/parking-control/user/admin/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/parking-control/user").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/parking-control/user/{id}").hasAnyRole("ADMIN", "USER")




                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
