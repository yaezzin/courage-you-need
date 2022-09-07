package com.app.zero.dto.push;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FcmRequestDto {
    private String targetToken;
    private String title;
    private String body;
}