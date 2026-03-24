package com.example.websocketservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SessionManager {

    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void addSession(String userId, WebSocketSession session) {

        sessions.put(userId, session);
        log.info("User connected: {}", userId);

    }

    public void removeSession(String userId) {

        sessions.remove(userId);
        log.info("User disconnected: {}", userId);

    }

    public WebSocketSession getSession(String userId) {

        return sessions.get(userId);

    }
}