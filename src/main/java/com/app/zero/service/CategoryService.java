package com.app.zero.service;

import com.app.zero.domain.board.BoardCategory;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.dto.category.CategoryRequestDto;
import com.app.zero.dto.category.CategoryResponseDto;
import com.app.zero.exception.board.BoardCategoryAlreadyExistException;
import com.app.zero.exception.board.BoardCategoryNotFoundException;
import com.app.zero.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        BoardCategory parent = Optional.ofNullable(requestDto.getParentIdx())
                .map(id -> categoryRepository.findById(id).orElseThrow(BoardCategoryNotFoundException::new))
                .orElse(null);

        BoardCategory boardCategory = categoryRepository.save(new BoardCategory(requestDto.getName(), parent));
        return new CategoryResponseDto(boardCategory);
    }

    public List<CategoryResponseDto> getAllCategory() {
        List<CategoryResponseDto> categories = categoryRepository.findAllCategory()
                .stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
        return categories;
    }

    @Transactional
    public Long deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return id;
    }
}
