package com.app.zero.service;

import com.app.zero.domain.user.User;
import com.app.zero.dto.user.SignUpRequestDto;
import com.app.zero.exception.user.UserNicknameAlreadyExistException;
import com.app.zero.exception.user.UserPhoneNumberAlreadyExistException;
import com.app.zero.exception.user.UserPhoneNumberRegexException;
import com.app.zero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* 회원 가입 */
    public User signUp(SignUpRequestDto requestDto) {
        validateSignUpInfo(requestDto);
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        return userRepository.save(new User(requestDto.getPhoneNumber(), encodedPassword, requestDto.getNickname()));
    }

    private void validateSignUpInfo(SignUpRequestDto requestDto) {
        User findUserPhoneNumber = userRepository.findByPhoneNumber(requestDto.getPhoneNumber());
        User findUserNickname = userRepository.findByNickname(requestDto.getNickname());

        if (findUserPhoneNumber != null) {
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
