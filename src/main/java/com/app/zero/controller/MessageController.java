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

    /* 메세지 조회 - 송신자 입장 */
    @GetMapping("/messages/sender")
    public MessageListDto readAllBySender(@RequestBody MessageReadCondition cond) {
        return messageService.getMessagesBySender(cond);
    }

    /* 메세지 조회 - 수신자 입장 */
    @GetMapping("/messages/receiver")
    public MessageListDto readAllByReceiver(@RequestBody MessageReadCondition cond) {
        return messageService.getMessagesByReceiver(cond);
    }

    /* 메세지 개별 조회*/
    @GetMapping("/messages/{id}")
    public MessageDto read(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    /* 메세지 개별 생성 */
    @PostMapping("/messages")
    public void create(@RequestBody MessageCreateRequestDto req) {
        messageService.create(req);
    }

    /* 메세지 삭제 - 송신자*/
    @DeleteMapping("/messages/sender/{id}")
    public void deleteBySender(@PathVariable Long id) {
        messageService.deleteBySender(id);
    }

    /* 메세지 삭제 - 수신자 */
    @DeleteMapping("/messages/receiver/{id}")
    public void deleteByReceiver(@PathVariable Long id) {
        messageService.deleteByReceiver(id);
    }
}
