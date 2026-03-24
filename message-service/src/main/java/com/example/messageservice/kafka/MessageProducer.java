package com.example.messageservice.kafka;

import com.example.messageservice.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message) {

        kafkaTemplate.send("message-topic", message);

    }
}