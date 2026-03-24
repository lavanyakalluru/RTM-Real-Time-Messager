package com.example.websocketservice.handler;

import com.example.websocketservice.service.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.*;

import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final SessionManager sessionManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

        String userId = session.getUri().getQuery().split("=")[1];

        sessionManager.addSession(userId, session);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) {

        String userId = session.getUri().getQuery().split("=")[1];

        sessionManager.removeSession(userId);
    }
}