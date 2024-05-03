package com.codegym.springsecurity.bai15_springsecurity_jwt.repository;

import com.codegym.springsecurity.bai15_springsecurity_jwt.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
