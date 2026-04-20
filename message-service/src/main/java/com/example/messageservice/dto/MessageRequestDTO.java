package com.example.messageservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequestDTO {

    private String senderId;
    private String receiverId;
    private String content;

}