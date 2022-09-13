package com.app.zero.repository;

import com.app.zero.domain.board.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<BoardCategory, Long> {
    Optional<String> findByNameContaining(String name);

    @Query("select c from BoardCategory c where c.parent is NULL")
    List<BoardCategory> findAllCategory();
}
