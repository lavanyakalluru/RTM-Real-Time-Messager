package com.example.messageservice.controller;

import com.example.messageservice.dto.MessageRequestDTO;
import com.example.messageservice.dto.MessageResponseDTO;
import com.example.messageservice.service.MessageService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public MessageResponseDTO sendMessage(
            @RequestBody MessageRequestDTO request) {

        return messageService.sendMessage(request);
    }

    @GetMapping("/conversation")
    public List<MessageResponseDTO> getConversation(
            @RequestParam String user1,
            @RequestParam String user2) {

        return messageService.getConversation(user1, user2);
    }
}