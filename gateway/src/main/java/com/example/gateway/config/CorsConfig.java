package com.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*");     // allow all origins
        config.addAllowedMethod("*");     // allow GET, POST, PUT, DELETE
        config.addAllowedHeader("*");     // allow all headers
        config.setAllowCredentials(true); // allow cookies or auth

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}