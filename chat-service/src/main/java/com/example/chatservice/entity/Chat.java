package com.example.chatservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "chats",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userPhone", "contactPhone"})
        }
)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userPhone;

    private String contactPhone;

    private String contactName;

    private String lastMessage;

    private LocalDateTime lastMessageTime;

    private LocalDateTime createdAt;

}