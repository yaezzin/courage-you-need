package com.app.zero.service;

import com.app.zero.domain.board.BoardCategory;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.dto.category.CategoryRequestDto;
import com.app.zero.dto.category.CategoryResponseDto;
import com.app.zero.exception.board.BoardCategoryAlreadyExistException;
import com.app.zero.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        if (categoryRepository.findByNameContaining(requestDto.getName()).isPresent()) {
            throw new BoardCategoryAlreadyExistException();
        }
        BoardCategory boardCategory = categoryRepository.save(new BoardCategory(requestDto.getName()));
        return new CategoryResponseDto(boardCategory);
    }

    public List<CategoryResponseDto> getAllCategory() {
        List<CategoryResponseDto> categories = categoryRepository.findAll()
                .stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
        return categories;
    }
}
