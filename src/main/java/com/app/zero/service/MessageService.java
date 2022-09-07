package com.app.zero.service;

import com.app.zero.domain.message.Message;
import com.app.zero.domain.user.User;
import com.app.zero.dto.message.*;
import com.app.zero.exception.MessageNotFoundException;
import com.app.zero.exception.user.UserNotFoundException;
import com.app.zero.repository.MessageRepository;
import com.app.zero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageListDto getMessagesBySender(MessageReadCondition cond) {
        return MessageListDto.toDto(
                messageRepository.findAllBySenderIdOrderByMessageIdDesc(cond.getMemberId(), cond.getLastMessageId(), Pageable.ofSize(cond.getSize()))
        );
    }

    public MessageListDto getMessagesByReceiver(MessageReadCondition cond) {
        return MessageListDto.toDto(
                messageRepository.findAllByReceiverIdOrderByMessageIdDesc(cond.getMemberId(), cond.getLastMessageId(), Pageable.ofSize(cond.getSize()))
        );
    }

    @PreAuthorize("@messageGuard.check(#id)")
    public MessageDto getMessageById(Long id) {
        return MessageDto.toDto(
                messageRepository.findWithSenderAndReceiverById(id).orElseThrow(MessageNotFoundException::new)
        );
    }

    @Transactional
    public void create(MessageCreateRequestDto req) {
        User sender = userRepository.findById(req.getMemberId()).orElseThrow(UserNotFoundException::new);
        User receiver = userRepository.findById(req.getReceiverId()).orElseThrow(UserNotFoundException::new);
        Message message = new Message(req.getContent(), sender, receiver);
        messageRepository.save(message);
    }

    @Transactional
    @PreAuthorize("@messageSenderGuard.check(#id)")
    public void deleteBySender(Long id) {
        delete(id, Message::deleteBySender);
    }

    @Transactional
    @PreAuthorize("@messageReceiverGuard.check(#id)")
    public void deleteByReceiver(Long id) {
        delete(id, Message::deleteByReceiver);
    }

    private void delete(Long id, Consumer<Message> delete) {
        Message message = messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);
        delete.accept(message);
        if(message.isDeletable()) {
            messageRepository.delete(message);
        }
    }
}
