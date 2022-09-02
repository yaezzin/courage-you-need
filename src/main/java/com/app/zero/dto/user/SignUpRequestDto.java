package com.app.zero.dto.user;

import com.app.zero.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {
    private String phoneNumber;
    private String password;
    private String nickname;

    public User toEntity(){
        return User.builder()
                .phoneNumber(phoneNumber)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
