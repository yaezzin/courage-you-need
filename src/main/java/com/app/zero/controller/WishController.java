package com.app.zero.controller;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.user.User;
import com.app.zero.exception.user.UserNotFoundException;
import com.app.zero.repository.UserRepository;
import com.app.zero.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class WishController {

    private final WishService wishService;
    private final UserRepository userRepository;

    @PostMapping("/wish/{boardIdx}")
    public void addWish(@PathVariable("boardIdx") Long id) {
        wishService.addWish(id, loginUser());
    }

    private User loginUser() {
        return userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
    }

}


