package com.example.messageservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponseDTO {

    private Long id;
    private String chatId;
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime timestamp;
    private String status;

}