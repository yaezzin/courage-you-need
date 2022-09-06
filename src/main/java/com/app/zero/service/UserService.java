package com.app.zero.service;

import com.app.zero.domain.user.User;
import com.app.zero.dto.user.UserResponseDto;
import com.app.zero.dto.user.UserUpdateRequestDto;
import com.app.zero.exception.user.UserNicknameAlreadyExistException;
import com.app.zero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
        List<User> allUser = userRepository.findAll();
        User user = userRepository.findById(requestDto.getUserIdx()).orElseThrow();

        // 유저 닉네임 변경 시 중복되는 닉네임인 경우 예외 발생
        for (User u : allUser) {
            if (u.getNickname() == requestDto.getNickname()) {
                throw new UserNicknameAlreadyExistException();
            }
        }
        user.update(requestDto.getNickname(), requestDto.getProfileImage());
        userRepository.save(user);
        return user.getId();
    }

    public Long delete(Long userIdx) {
        userRepository.deleteById(userIdx);
        return userIdx;
    }
}
