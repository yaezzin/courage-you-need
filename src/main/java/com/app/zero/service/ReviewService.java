package com.app.zero.service;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.review.Review;
import com.app.zero.domain.user.User;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.dto.review.ReviewCreateRequestDto;
import com.app.zero.dto.review.ReviewResponseDto;
import com.app.zero.repository.ReviewRepository;
import com.app.zero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public ReviewResponseDto create(ReviewCreateRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
        requestDto.setUser(user); // 유저 정보를 가져와서 Dto에 담아준다
        Review review = reviewRepository.save(requestDto.toEntity());
        return new ReviewResponseDto(review);
    }

    public List<ReviewResponseDto> getReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }
}