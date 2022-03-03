package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(String userId);

    List<User> findAll();
}