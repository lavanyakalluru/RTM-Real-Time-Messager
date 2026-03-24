package com.example.chatservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateChatRequest {


    @NotBlank
    private String contactPhone;

    @NotBlank
    private String contactName;

}