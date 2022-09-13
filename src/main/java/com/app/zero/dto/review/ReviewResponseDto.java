package com.app.zero.dto.review;

import com.app.zero.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponseDto {
    private Long reviewIdx;
    private String kakaoStoreIdx;
    private String title;
    private String description;
    private double volume;
    private double courageStar;
    private double tasteStar;
    private String nickname;

    public ReviewResponseDto(Review review) {
        this.reviewIdx = review.getId();
        this.kakaoStoreIdx = review.getKakaoStoreIdx();
        this.title = review.getTitle();
        this.description = review.getDescription();
        this.volume = review.getVolume();
        this.courageStar = review.getCourageScore();
        this.tasteStar = review.getTasteScore();
        this.nickname = review.getUser().getNickname();
    }
}
