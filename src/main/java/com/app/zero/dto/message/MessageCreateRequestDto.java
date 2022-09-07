package com.app.zero.dto.message;

import com.app.zero.domain.message.Message;
import com.app.zero.exception.user.UserNotFoundException;
import com.app.zero.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageCreateRequestDto {

    private String content;
    private Long memberId;
    private Long receiverId;

    public static Message toEntity(MessageCreateRequestDto req, UserRepository memberRepository) {
        return new Message(
                req.content,
                memberRepository.findById(req.memberId).orElseThrow(UserNotFoundException::new),
                memberRepository.findById(req.receiverId).orElseThrow(UserNotFoundException::new)
        );
    }
}
