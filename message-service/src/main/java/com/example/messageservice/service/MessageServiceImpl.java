package com.example.messageservice.service;

import com.example.messageservice.dto.MessageRequestDTO;
import com.example.messageservice.dto.MessageResponseDTO;
import com.example.messageservice.entity.Message;
import com.example.messageservice.kafka.MessageProducer;
import com.example.messageservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    private final MessageProducer producer;

    @Override
    public MessageResponseDTO sendMessage(MessageRequestDTO request) {

        String chatId = generateChatId(
                request.getSenderId(),
                request.getReceiverId()
        );

        Message message = Message.builder()
                .id(Long.valueOf(UUID.randomUUID().toString())) // ✅ unique ID
                .chatId(chatId)
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .content(request.getContent())
                .timestamp(LocalDateTime.now())
                .status("SENT")
                .build();

        // ❌ NO DB SAVE HERE
        producer.sendMessage(message);

        return mapToDTO(message); // immediate response
    }

    private String generateChatId(String user1, String user2) {

        user1 = user1.trim();
        user2 = user2.trim();

        return user1.compareTo(user2) < 0
                ? user1 + "_" + user2
                : user2 + "_" + user1;
    }

    @Override
    public List<MessageResponseDTO> getConversation(String user1, String user2) {

        String chatId = generateChatId(user1, user2);

        return repository.findByChatIdOrderByTimestampAsc(chatId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private MessageResponseDTO mapToDTO(Message message) {

        return MessageResponseDTO.builder()
                .id(message.getId())
                .chatId(message.getChatId())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .status(message.getStatus())
                .build();
    }
}