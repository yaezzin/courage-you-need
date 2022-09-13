package com.app.zero.domain.review;

import com.app.zero.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kakaoStoreIdx; // 카카오 지도에서 가져온 가게의 idx값

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private double volume; //용량

    @Column
    private double courageScore; //용기내 별점 -> 후에 스트링으로 변환해서 프론트로 보내주기cription;

    @Column
    private double tasteScore; // 맛 별점

    //private String photo;

    //private String category; // 계층형으로 시도해보기

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Review(User user, String kakaoStoreIdx, String title, String description, double volume, double courageScore, double tasteScore) {
        this.user = user;
        this.kakaoStoreIdx = kakaoStoreIdx;
        this.title = title;
        this.description = description;
        this.volume = volume;
        this.courageScore = courageScore;
        this.tasteScore = tasteScore;
    }
}
