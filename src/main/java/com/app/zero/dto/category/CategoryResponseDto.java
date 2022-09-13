package com.app.zero.dto.category;

import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    private Long categoryIdx;
    private String name;
    private List<CategoryResponseDto> children;

    public CategoryResponseDto(BoardCategory boardCategory) {
        this.categoryIdx = boardCategory.getId();
        this.name = boardCategory.getName();
        this.children = boardCategory
                .getChildren()
                .stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }
}
