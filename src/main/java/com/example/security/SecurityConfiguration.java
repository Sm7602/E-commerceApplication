package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {

        http

        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
                .requestMatchers( "/api/auth/**","/api/v1/**")
                .permitAll()
                
                .requestMatchers(HttpMethod.POST,"/api/orders/**")
                .hasRole("CUSTOMER")

                .requestMatchers("/api/v1/cart/**")
                .hasRole("CUSTOMER")

                .requestMatchers("/api/wishlist/**")
                .hasRole("CUSTOMER")

                .requestMatchers(HttpMethod.POST,"/api/products/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT,"/api/products/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE,"/api/products/**")
                .hasRole("ADMIN")

                .requestMatchers("/api/categories/**")
                .hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET,"/api/products/**")
                .authenticated()

                .requestMatchers(HttpMethod.GET,"/api/customer/**")
                .authenticated()
                .anyRequest()
                .authenticated()

        )


        .sessionManagement(session ->

                session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS)
        )


        .authenticationProvider(
                authenticationProvider
        )


        .addFilterBefore(
                jwtAuthFilter,
                UsernamePasswordAuthenticationFilter.class
        );


return http.build();
    }
}

