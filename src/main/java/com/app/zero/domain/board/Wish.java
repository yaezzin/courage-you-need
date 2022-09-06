package com.app.zero.domain.board;

import com.app.zero.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Board board;

    @ManyToOne
    private User user;

    public Wish(Board board, User user) {
        this.board = board;
        this.user = user;
    }
}
