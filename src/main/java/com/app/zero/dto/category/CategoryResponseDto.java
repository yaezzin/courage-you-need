package com.app.zero.dto.category;

import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    private Long categoryIdx;
    private String name;

    public CategoryResponseDto(BoardCategory boardCategory) {
        this.categoryIdx = boardCategory.getId();
        this.name = boardCategory.getName();
    }
}
