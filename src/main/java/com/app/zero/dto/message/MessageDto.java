package com.app.zero.dto.message;

import com.app.zero.domain.message.Message;
import com.app.zero.dto.user.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private String content;
    private UserResponseDto sender;
    private UserResponseDto receiver;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getContent(),
                UserResponseDto.toDto(message.getSender()),
                UserResponseDto.toDto(message.getReceiver()),
                message.getCreatedAt());
    }
}
