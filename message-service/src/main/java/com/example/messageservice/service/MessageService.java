package com.example.messageservice.service;

import com.example.messageservice.dto.MessageRequestDTO;
import com.example.messageservice.dto.MessageResponseDTO;

import java.util.List;

public interface MessageService {

    MessageResponseDTO sendMessage(MessageRequestDTO request);

    List<MessageResponseDTO> getConversation(String user1,String user2);

}