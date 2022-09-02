package com.app.zero.service;

import com.app.zero.domain.user.User;
import com.app.zero.dto.user.UserResponseDto;
import com.app.zero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto findById(Long userIdx) {
        User user = userRepository.findById(userIdx).orElseThrow();
        return new UserResponseDto(user.getUserIdx(), user.getPhoneNumber(), user.getNickname());
    }

}
