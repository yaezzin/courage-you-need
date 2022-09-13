package com.app.zero.controller;

import com.app.zero.dto.review.ReviewCreateRequestDto;
import com.app.zero.dto.review.ReviewResponseDto;
import com.app.zero.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseDto> create(@RequestBody ReviewCreateRequestDto requestDto) {
        return new ResponseEntity<>(reviewService.create(requestDto), HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getReviews() {
        return new ResponseEntity<>(reviewService.getReviews(), HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewIdx}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable("reviewIdx") Long id) {
        return new ResponseEntity<>(reviewService.getReview(id), HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewIdx}")
    public String delete(@PathVariable("reviewIdx") Long id) {
        reviewService.delete(id);
        return "성공적으로 삭제되었습니다.";
    }
}
