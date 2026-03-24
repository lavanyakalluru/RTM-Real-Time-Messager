package com.example.websocketservice.kafka;

import com.example.websocketservice.model.MessageEvent;
import com.example.websocketservice.service.SessionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final SessionManager sessionManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "message-topic", groupId = "websocket-group")
    public void consume(MessageEvent event) {

        try {

            WebSocketSession session =
                    sessionManager.getSession(event.getReceiverId());

            if (session != null && session.isOpen()) {

                String json = objectMapper.writeValueAsString(event);

                session.sendMessage(new TextMessage(json));

            }

        } catch (Exception e) {

            log.error("Error delivering message", e);

        }
    }
}