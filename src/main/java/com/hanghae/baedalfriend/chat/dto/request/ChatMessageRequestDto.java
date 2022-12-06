package com.hanghae.baedalfriend.chat.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanghae.baedalfriend.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageRequestDto {
    private ChatMessage.MessageType type;
    private Long roomId;
    private String message;
    private String sender;
    private String createdAt;
}