package com.app.zero.dto.user;

import com.app.zero.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CUserDto {
    private Long id;
    private String phoneNumber;
    private String nickname;

    public static CUserDto toDto(User member) {
        return new CUserDto(member.getId(), member.getPhoneNumber(), member.getNickname());
    }

    public static CUserDto empty() {
        return new CUserDto(null, "", "");
    }
}
