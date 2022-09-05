package com.app.zero.dto.comment;

import com.app.zero.domain.board.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private String comment;

    public Comment toEntity(){
        return Comment.builder()
                .comment(comment)
                .build();
    }
}
