package com.app.zero.controller;

import com.app.zero.dto.message.MessageCreateRequestDto;
import com.app.zero.dto.message.MessageDto;
import com.app.zero.dto.message.MessageListDto;
import com.app.zero.dto.message.MessageReadCondition;
import com.app.zero.service.MessageService;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/messages/sender")
    public MessageListDto readAllBySender(@RequestBody MessageReadCondition cond) {
        return messageService.readAllBySender(cond);
    }

    @GetMapping("/messages/receiver")
    public MessageListDto readAllByReceiver(@RequestBody MessageReadCondition cond) {
        return messageService.readAllByReceiver(cond);
    }

    @GetMapping("/messages/{id}")
    public MessageDto read(@PathVariable Long id) {
        return messageService.read(id);
    }

    @PostMapping("/messages")
    public void create(@RequestBody MessageCreateRequestDto req) {
        messageService.create(req);
    }

    @DeleteMapping("/messages/sender/{id}")
    public void deleteBySender(@PathVariable Long id) {
        messageService.deleteBySender(id);
    }

    @DeleteMapping("/messages/receiver/{id}")
    public void deleteByReceiver(@PathVariable Long id) {
        messageService.deleteByReceiver(id);
    }
}
