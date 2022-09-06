package com.app.zero.dto.comment;

import com.app.zero.domain.board.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentIdx;
    private String comment;
    private Long likeCount;
    private String nickname;
    private String profileImage;
    private LocalDateTime modifiedAtComment;

    public CommentResponseDto(Comment comment) {
        this.commentIdx = comment.getId();
        this.comment = comment.getComment();
        this.likeCount = comment.getLikes().stream().count();
        this.nickname = comment.getUser().getNickname();
        this.profileImage = comment.getUser().getProfileImage();
        this.modifiedAtComment = comment.getModifiedAt();
    }
}
