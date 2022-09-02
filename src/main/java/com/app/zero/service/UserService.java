package com.app.zero.service;

import com.app.zero.domain.user.User;
import com.app.zero.dto.user.UserResponseDto;
import com.app.zero.dto.user.UserUpdateRequestDto;
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

    public Long update(UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserIdx()).orElseThrow();
        user.update(requestDto.getNickname(), requestDto.getProfileImage());
        return user.getUserIdx();
    }

    public Long delete(Long userIdx) {
        userRepository.deleteById(userIdx);
        return userIdx;
    }
}
