package com.app.zero.repository;
import com.app.zero.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    User findByNickname(String nickname);

    boolean existsUserByPhoneNumber(String phoneNumber);
}
