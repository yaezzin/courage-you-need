package com.app.zero.domain.board;

import com.app.zero.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    private int viewCount;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private BoardCategory category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Wish> wish = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(String title, String description, User user, BoardCategory category) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
