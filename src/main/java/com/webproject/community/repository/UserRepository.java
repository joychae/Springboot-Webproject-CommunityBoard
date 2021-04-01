package com.webproject.community.repository;

import com.webproject.community.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // username, kakaoId, email 각각을 기준으로 User 객체 값을 찾는 명령문 입니다.
    // 값이 없을 수도 있기 때문에 List 대신 Optional 을 사용하였습니다.
    Optional<User> findByUsername(String username);
    Optional<User> findByKakaoId(Long kakaoId);
    Optional<User> findByEmail(String email);
}
