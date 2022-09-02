package com.app.zero.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(nullable = false, length = 11, unique = true)
    private String phoneNumber;

    private String password;

    @Column(length = 20, unique = true)
    private String nickname;

    private String profileImage;

    public User(String phoneNumber, String password, String nickname) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.nickname = nickname;
    }
}
