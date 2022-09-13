package com.app.zero.dto.user;

import com.app.zero.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long userIdx;
    private String phoneNumber;
    private String nickname;

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getPhoneNumber(), user.getNickname());
    }

    public static UserResponseDto empty() {
        return new UserResponseDto(null, "", "");
    }
}
