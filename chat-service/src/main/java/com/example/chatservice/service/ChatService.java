package com.example.chatservice.service;

import com.example.chatservice.dto.ChatResponse;
import com.example.chatservice.dto.CreateChatRequest;

import java.util.List;

public interface ChatService {

    ChatResponse createChat(String userPhone, CreateChatRequest request);

    List<ChatResponse> getUserChats(String userPhone);

    void deleteChat(String userPhone, String contactPhone);

}