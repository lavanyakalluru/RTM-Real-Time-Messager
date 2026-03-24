package com.example.chatservice.controller;

import com.example.chatservice.dto.ChatResponse;
import com.example.chatservice.dto.CreateChatRequest;
import com.example.chatservice.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("createChat")
    public ChatResponse createChat(
            @RequestHeader("X-User-Phone") String userPhone,
            @RequestBody @Valid CreateChatRequest request) {

        return chatService.createChat(userPhone, request);
    }

    @GetMapping("getUserChats")
    public List<ChatResponse> getUserChats(
            @RequestHeader("X-User-Phone") String userPhone) {

        return chatService.getUserChats(userPhone);
    }

    @DeleteMapping("deleteChat")
    public void deleteChat(
            @RequestHeader("X-User-Phone") String userPhone,
            @RequestParam String contactPhone) {

        chatService.deleteChat(userPhone, contactPhone);
    }
}