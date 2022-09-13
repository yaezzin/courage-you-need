package com.app.zero.controller;

import com.app.zero.dto.review.ReviewCreateRequestDto;
import com.app.zero.dto.review.ReviewResponseDto;
import com.app.zero.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDto> create(@RequestBody ReviewCreateRequestDto requestDto) {
        return new ResponseEntity<>(reviewService.create(requestDto), HttpStatus.OK);
    }

}
