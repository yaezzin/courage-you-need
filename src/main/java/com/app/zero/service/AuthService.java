package com.app.zero.service;

import com.app.zero.config.jwt.JwtTokenProvider;
import com.app.zero.domain.user.User;
import com.app.zero.dto.user.*;
import com.app.zero.exception.user.UserNicknameAlreadyExistException;
import com.app.zero.exception.user.UserPhoneNumberAlreadyExistException;
import com.app.zero.exception.user.UserPhoneNumberRegexException;
import com.app.zero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /* 회원 가입 */
    @Transactional
    public UserResponseDto signUp(SignUpRequestDto requestDto) {
        validateSignUpInfo(requestDto);
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = userRepository.save(
                User.builder()
                        .phoneNumber(requestDto.getPhoneNumber())
                        .password(encodedPassword)
                        .nickname(requestDto.getNickname())
                        .build());

        return new UserResponseDto(user.getUserIdx(), user.getPhoneNumber(), user.getNickname());
    }

    /* 로그인 */
    @Transactional
    public LoginResponseDto login(LoginRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(requestDto.getPhoneNumber()).orElseThrow();

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new LoginResponseDto(user.getUserIdx(), requestDto.getPhoneNumber(), jwtTokenProvider.createToken(requestDto.getPhoneNumber()));
    }

    private void validateSignUpInfo(SignUpRequestDto requestDto) {
        boolean existUserByPhoneNumber = userRepository.existsUserByPhoneNumber(requestDto.getPhoneNumber());
        User findUserNickname = userRepository.findByNickname(requestDto.getNickname());

        if (existUserByPhoneNumber) {
            throw new UserPhoneNumberAlreadyExistException();
        }

        if (findUserNickname != null) {
            throw new UserNicknameAlreadyExistException();
        }

        if (!isRegexPhoneNumber(requestDto.getPhoneNumber())) {
            throw new UserPhoneNumberRegexException();
        }
    }

    private boolean isRegexPhoneNumber(String phoneNumber) {
        String regex = "^\\d{11}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }
}

