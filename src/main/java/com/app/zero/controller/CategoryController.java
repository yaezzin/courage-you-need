package com.app.zero.controller;

import com.app.zero.domain.board.BoardCategory;
import com.app.zero.dto.category.CategoryRequestDto;
import com.app.zero.dto.category.CategoryResponseDto;
import com.app.zero.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    /* Board 카테고리 생성 (계층형 X) */
    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto requestDto) {
        return new ResponseEntity<>(categoryService.createCategory(requestDto), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

}
