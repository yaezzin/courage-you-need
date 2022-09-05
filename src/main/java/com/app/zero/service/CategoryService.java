package com.app.zero.service;

import com.app.zero.domain.board.BoardCategory;
import com.app.zero.dto.category.CategoryRequestDto;
import com.app.zero.dto.category.CategoryResponseDto;
import com.app.zero.exception.user.UserNotFoundException;
import com.app.zero.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        if (categoryRepository.findByNameContaining(requestDto.getName()).isPresent()) {
            throw new UserNotFoundException();
        }
        BoardCategory boardCategory = categoryRepository.save(new BoardCategory(requestDto.getName()));
        return new CategoryResponseDto(boardCategory);
    }
}
