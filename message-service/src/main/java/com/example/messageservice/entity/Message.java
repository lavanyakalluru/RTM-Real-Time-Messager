package com.example.messageservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;

    private String senderId;

    private String receiverId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime timestamp;

    private String status;
}