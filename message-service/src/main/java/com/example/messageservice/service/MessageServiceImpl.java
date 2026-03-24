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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    private final MessageProducer producer;

    @Override
    public MessageResponseDTO sendMessage(MessageRequestDTO request) {

        Message message = Message.builder()
                .chatId(request.getChatId())
                .senderId(request.getSenderId())
                .receiverId(request.getReceiverId())
                .content(request.getContent())
                .timestamp(LocalDateTime.now())
                .status("SENT")
                .build();

        Message saved = repository.save(message);

        producer.sendMessage(saved);

        return mapToDTO(saved);
    }

    @Override
    public List<MessageResponseDTO> getMessages(String chatId) {

        return repository.findByChatIdOrderByTimestampAsc(chatId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
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