package com.app.zero.service;

import com.app.zero.domain.user.User;
import com.app.zero.dto.board.BoardListResponseDto;
import com.app.zero.dto.user.UserResponseDto;
import com.app.zero.dto.user.UserUpdateRequestDto;
import com.app.zero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserResponseDto(user.getId(), user.getPhoneNumber(), user.getNickname());
    }

    public Long update(UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserIdx()).orElseThrow();
        user.update(requestDto.getNickname(), requestDto.getProfileImage());
        return user.getId();
    }

    public Long delete(Long userIdx) {
        userRepository.deleteById(userIdx);
        return userIdx;
    }
}
