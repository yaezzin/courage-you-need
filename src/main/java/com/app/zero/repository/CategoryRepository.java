package com.app.zero.repository;

import com.app.zero.domain.board.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<BoardCategory, Long> {
    Optional<String> findByNameContaining(String name);
}
