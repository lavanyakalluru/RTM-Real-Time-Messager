package com.example.websocketservice.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageEvent {

    private Long id;
    private String chatId;
    private String senderId;
    private String receiverId;
    private String content;
    private LocalDateTime timestamp;
    private String status;

}