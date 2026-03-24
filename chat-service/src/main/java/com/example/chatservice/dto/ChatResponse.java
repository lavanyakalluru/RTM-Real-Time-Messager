package com.example.chatservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatResponse {

    private String contactPhone;

    private String contactName;

    private String lastMessage;

    private LocalDateTime lastMessageTime;

}