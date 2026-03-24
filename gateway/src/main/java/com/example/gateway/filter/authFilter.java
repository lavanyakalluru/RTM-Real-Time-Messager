package com.example.gateway.filter;

import com.example.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class authFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        System.out.println("Incoming request path: " + path);

        if (path.equals("/users/register") || path.equals("/users/login")) {
            System.out.println("Public endpoint, skipping authentication");
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        System.out.println("Authorization header received: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            System.out.println("Authorization header missing or invalid");

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        System.out.println("Extracted JWT token: " + token);

        boolean valid = jwtUtil.validateToken(token);

        System.out.println("Token validation result: " + valid);

        if (!valid) {

            System.out.println("Token validation failed");

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String phone = jwtUtil.extractPhone(token);

        System.out.println("Extracted user phone from token: " + phone);

        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .header("X-User-Phone", phone)
                        .build())
                .build();

        System.out.println("X-User-Phone header added to request");

        return chain.filter(mutatedExchange);
    }
}