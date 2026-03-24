package com.example.chatservice.service;

import com.example.chatservice.dto.ChatResponse;
import com.example.chatservice.dto.CreateChatRequest;
import com.example.chatservice.entity.Chat;
import com.example.chatservice.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public ChatResponse createChat(String userPhone, CreateChatRequest request) {

        Chat chat = chatRepository
                .findByUserPhoneAndContactPhone(userPhone, request.getContactPhone())
                .orElseGet(() -> {

                    Chat newChat = Chat.builder()
                            .userPhone(userPhone)
                            .contactPhone(request.getContactPhone())
                            .contactName(request.getContactName())
                            .createdAt(LocalDateTime.now())
                            .build();

                    return chatRepository.save(newChat);
                });

        return ChatResponse.builder()
                .contactPhone(chat.getContactPhone())
                .contactName(chat.getContactName())
                .lastMessage(chat.getLastMessage())
                .lastMessageTime(chat.getLastMessageTime())
                .build();
    }
    @Override
    public List<ChatResponse> getUserChats(String userPhone) {

        return chatRepository.findByUserPhone(userPhone)
                .stream()
                .map(chat -> ChatResponse.builder()
                        .contactPhone(chat.getContactPhone())
                        .contactName(chat.getContactName())
                        .lastMessage(chat.getLastMessage())
                        .lastMessageTime(chat.getLastMessageTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteChat(String userPhone, String contactPhone) {

        chatRepository
                .findByUserPhoneAndContactPhone(userPhone, contactPhone)
                .ifPresent(chatRepository::delete);

    }
}