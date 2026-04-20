package com.example.messageservice.kafka;

import com.example.messageservice.entity.Message;
import com.example.messageservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageConsumer {

    private final MessageRepository repository;

    @KafkaListener(topics = "message-topic", groupId = "message-group")
    public void consume(Message message) {

        // ✅ Idempotency check (avoid duplicates)
        if (repository.existsById(message.getId())) {
            return;
        }

        repository.save(message); // ✅ DB write happens here
    }
}