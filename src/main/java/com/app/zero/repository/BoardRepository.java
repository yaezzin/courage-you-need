package com.app.zero.repository;

import com.app.zero.domain.board.Board;
import com.app.zero.dto.board.BoardResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Query("update Board b set b.viewCount = b.viewCount + 1 where b.id = :id")
    int updateViewCount(Long id);

    @Query("select b from Board b order by b.viewCount desc")
    List<Board> findAllOrderByViewCountDesc();

}
