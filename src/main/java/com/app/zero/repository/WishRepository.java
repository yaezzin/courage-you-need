package com.app.zero.repository;

import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.Wish;
import com.app.zero.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    // 동일한 글에 동일한 계정으로 이미 좋아요한 내역이 있는지 찾을 때 사용할 메소드
    Optional<Wish> findByUserAndBoard(User user, Board board);
    Wish getWishByUserAndBoard(User user, Board board);

}
