package com.app.zero.repository;

import com.app.zero.domain.board.Comment;
import com.app.zero.domain.board.CommentLike;
import com.app.zero.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
   Optional<CommentLike> findByUserAndComment(User user, Comment comment);
   CommentLike getCommentLikeByUserAndComment(User user, Comment comment);
}
