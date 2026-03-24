package com.example.websocketservice.config;

import com.example.websocketservice.handler.ChatWebSocketHandler;
import com.example.websocketservice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final SessionManager sessionManager;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(
                new ChatWebSocketHandler(sessionManager),
                "/chat"
        ).setAllowedOrigins("*");

    }
}