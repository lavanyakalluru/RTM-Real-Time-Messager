package com.example.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayRoutes {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("user-service",
                        r -> r.path("/users/**")
                                .uri("lb://USER-SERVICE"))

                .route("message-service",
                        r -> r.path("/messages/**")
                                .uri("lb://MESSAGE-SERVICE"))

                .route("chat-service",
                        r -> r.path("/chats/**")
                                .uri("lb://CHAT-SERVICE"))

                .build();
    }
}