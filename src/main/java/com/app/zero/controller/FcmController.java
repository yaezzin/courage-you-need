package com.app.zero.controller;

import com.app.zero.dto.push.FcmRequestDto;
import com.app.zero.service.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FcmController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @PostMapping("/push")
    public ResponseEntity pushMessage(@RequestBody FcmRequestDto requestDto) throws IOException {
        firebaseCloudMessageService.sendMessageTo(
                requestDto.getTargetToken(),
                requestDto.getTitle(),
                requestDto.getBody()
        );
        return ResponseEntity.ok().build();
    }
}
