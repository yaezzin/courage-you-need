package com.app.zero.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private Long userIdx;
    private String phoneNumber;
    private String accessToken;
}
