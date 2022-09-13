package com.app.zero.dto.review;

import com.app.zero.domain.review.Review;
import com.app.zero.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequestDto {
    private String title;
    private String description;
    private double volume;
    private double courageScore;
    private double tasteScore;
    private User user;
    private String kakaoStoreIdx;

    public Review toEntity() {
        return Review.builder()
                .user(user)
                .kakaoStoreIdx(kakaoStoreIdx)
                .title(title)
                .description(description)
                .volume(volume)
                .courageScore(courageScore)
                .tasteScore(tasteScore)
                .build();
    }
}
