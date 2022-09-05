package com.app.zero.controller;

import com.app.zero.domain.board.BoardCategory;
import com.app.zero.dto.category.CategoryRequestDto;
import com.app.zero.dto.category.CategoryResponseDto;
import com.app.zero.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    /* 게시물 생성 */
    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto requestDto) {
        return new ResponseEntity<>(categoryService.createCategory(requestDto), HttpStatus.OK);
    }
}
