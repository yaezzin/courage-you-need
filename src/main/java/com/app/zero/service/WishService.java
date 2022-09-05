package com.app.zero.service;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.Wish;
import com.app.zero.domain.user.User;
import com.app.zero.exception.board.BoardNotFoundException;
import com.app.zero.exception.user.UserNotFoundException;
import com.app.zero.repository.BoardRepository;
import com.app.zero.repository.UserRepository;
import com.app.zero.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WishService {

    private final WishRepository wishRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void addWish(Long id, User user) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException());

        if (isNotAlreadyWish(user, board)) {
            wishRepository.save(new Wish(board, user));
        }
        else {
            Wish wish = wishRepository.getWishByUserAndBoard(user, board);
            wishRepository.delete(wish);
        }
    }

    private boolean isNotAlreadyWish(User user, Board board) {
        return wishRepository.findByUserAndBoard(user, board).isEmpty();
    }
}
