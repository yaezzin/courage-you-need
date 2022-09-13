package com.app.zero.domain.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class BoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private BoardCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<BoardCategory> children = new ArrayList<>();

    public BoardCategory(String name, BoardCategory parent) {
        this.name = name;
        this.parent = parent;
    }

}
