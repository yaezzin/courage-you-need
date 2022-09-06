package com.app.zero.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {
    private Long userIdx;
    private String nickname;
    private String profileImage;
}
