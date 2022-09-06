package com.app.zero.domain.board;

import com.app.zero.config.util.BaseEntity;
import com.app.zero.domain.user.User;
import com.app.zero.dto.comment.CommentUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Board board;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL) // 댓글 좋아요 수
    private List<CommentLike> likes = new ArrayList<>();

    @Builder
    public Comment(String comment, User user, Board board) {
        this.comment = comment;
        this.user = user;
        this.board = board;
    }

    public void addCommentInfo(User user, Board board){
        this.user = user;
        this.board = board;
    }

    public void updateComment(CommentUpdateRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

}
